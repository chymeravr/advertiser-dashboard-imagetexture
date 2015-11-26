package co.chimeralabs.advertiser.server.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.chimeralabs.advertiser.server.model.AdGroup;
import co.chimeralabs.advertiser.server.model.AdType;
import co.chimeralabs.advertiser.server.model.Advertiser;
import co.chimeralabs.advertiser.server.model.Campaign;
import co.chimeralabs.advertiser.server.model.UserPrincipal;
import co.chimeralabs.advertiser.server.service.AdGroupService;
import co.chimeralabs.advertiser.server.service.CampaignService;
import co.chimeralabs.advertiser.server.util.TypeConversion;

@Controller
public class AdvertiserDashboard {
	private static final Logger logger = LoggerFactory.getLogger(AdvertiserDashboard.class);
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	AdGroupService adGroupService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String homeMethod(Model m){
		return "jsp/home";
	}
	
	@RequestMapping(value="/template", method=RequestMethod.GET)
	public String templateMethod(Model m){
		return "template";
	}
	
	@RequestMapping(value="/dashboard/cm/ui/_ac/{actionId}", method = RequestMethod.GET)
	public String mainController(Model m, @PathVariable final String actionId, @RequestParam Map<String, String> params){
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long advertiserId = user.getAdvertiserId();
		if(actionId.equals("gcmt")){//getcampaignMainTemplate
			m.addAttribute("campaignTree", campaignService.getCampaignTree(advertiserId));
			return "jsp/campaignManagementDashboard";
		}
		if(actionId.equals("gacf")){//getAddCampaignForm
			m.addAttribute("adTypeValues", AdType.getEnumValues());
			m.addAttribute("adTypeNames", AdType.getEnumNames());
			return "jsp/addCampaignForm";
		}
		if(actionId.equals("gagf")){//getAdGroupForm
			//m.addAttribute("campaignId", params.get("campaignId"));
			Long campaignId = null;
			if(params.get("cid") != null){
				campaignId = TypeConversion.StringToLong(params.get("cid"));
				m.addAttribute("campaignId", campaignId);
			}
			else{
				List<Campaign> campaigns = campaignService.getCampaigns(advertiserId);
				m.addAttribute("campaigns", campaigns);
			}
			m.addAttribute("adGroup", new AdGroup());
			return "jsp/addAdGroupForm";
		}
		return "template";
	}
	
	@RequestMapping(value="/dashboard/cm/addCampaign", method = RequestMethod.POST)
	public String addCampaign(@RequestParam Map<String, String> params){
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long advertiserId = user.getAdvertiserId();
		Campaign campaign = new Campaign();
		campaign.setName(params.get("name"));
		campaign.setAdType(Enum.valueOf(AdType.class, params.get("adType")));
		if(campaign.getAdType() == null)
			logger.debug("CampaignService: Error in conversion of adType into enum");
		campaign.setDailyBudget(TypeConversion.StringToDouble(params.get("dailyBudget")));
		campaign.setStartDate(TypeConversion.StringToDate(params.get("startDate"), "yyyy/MM/dd hh:mm")); //2016/01/23 20:40
		if(campaign.getStartDate() == null)
			logger.debug("CampaignService: Error in conversion of startDate into Date format");
		campaign.setEndDate(TypeConversion.StringToDate(params.get("endDate"), "yyyy/MM/dd hh:mm"));
		if(campaign.getStartDate() == null)
			logger.debug("CampaignService: Error in conversion of endDate into Date format");
		campaign = campaignService.saveCampaign(campaign, advertiserId);
		if(campaign!=null && campaign.getCampaignId()!=null)
			logger.debug("Campaign saved with campaignId "+campaign.getCampaignId());
		else
			logger.debug("Unable to save campaign for advertiserid="+advertiserId);
		return "redirect:/dashboard/cm/ui/_ac/gcmt";
	}
	
	@RequestMapping(value="/dashboard/cm/addAdGroup", method = RequestMethod.POST)
	public String addAdGroup(@ModelAttribute("adGroup") AdGroup adGroup){
		adGroup = adGroupService.saveAdGroup(adGroup, adGroup.getCampaign().getCampaignId());
		return "redirect:/dashboard/cm/ui/_ac/gcmt";
	}
}
