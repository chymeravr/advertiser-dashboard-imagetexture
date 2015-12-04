package co.chimeralabs.advertiser.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.chimeralabs.advertiser.server.formDTO.AdGroupPerformanceDataDTO;
import co.chimeralabs.advertiser.server.formDTO.AdPerformanceDataDTO;
import co.chimeralabs.advertiser.server.formDTO.CampaignPerformanceDataDTO;
import co.chimeralabs.advertiser.server.formDTO.ImageAdUploadFormDTO;
import co.chimeralabs.advertiser.server.model.Ad;
import co.chimeralabs.advertiser.server.model.AdGroup;
import co.chimeralabs.advertiser.server.model.AdType;
import co.chimeralabs.advertiser.server.model.Campaign;
import co.chimeralabs.advertiser.server.model.TextureImageFormat;
import co.chimeralabs.advertiser.server.model.UserPrincipal;
import co.chimeralabs.advertiser.server.repository.TextureImageFormatRepository;
import co.chimeralabs.advertiser.server.service.AdGroupService;
import co.chimeralabs.advertiser.server.service.AdService;
import co.chimeralabs.advertiser.server.service.CampaignService;
import co.chimeralabs.advertiser.server.util.TypeConversion;

@Controller
public class AdvertiserDashboard {
	private static final Logger logger = LoggerFactory.getLogger(AdvertiserDashboard.class);
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	AdGroupService adGroupService;
	
	@Autowired
	AdService adService;
	
	@Autowired
	TextureImageFormatRepository textureImageFormatRepository;
	
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
			List<Campaign> campaignTree = campaignService.getCampaignTree(advertiserId);
			m.addAttribute("campaignTree", campaignTree);
			return "jsp/campaignManagementDashboard";
		}
		if(actionId.equals("gacf")){//getAddCampaignForm
			m.addAttribute("adTypeValues", AdType.getEnumValues());
			m.addAttribute("adTypeNames", AdType.getEnumNames());
			return "jsp/addCampaignForm";
		}
		else if(actionId.equals("gagf")){//getAdGroupForm
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
		else if(actionId.equals("gadf")){//getAdForm
			Long adGroupId = null;
			if(params.get("agid") != null){
				adGroupId = TypeConversion.StringToLong(params.get("agid"));
				m.addAttribute("adGroupId", adGroupId);
			}
			else if(params.get("cid") != null){
				Long campaignId = TypeConversion.StringToLong(params.get("cid"));
				List<AdGroup> adGroups = adGroupService.getAdGroups(campaignId);
				m.addAttribute("adGroups", adGroups);
			}
			else{
				List<Campaign> campaignTree = campaignService.getCampaignTree(advertiserId);
				m.addAttribute("campaignTree", campaignTree);
			}
			List<TextureImageFormat> imageFormats = textureImageFormatRepository.findAll();
			m.addAttribute("imageFormats", imageFormats);
			return "jsp/addAdForm";
		}
		
		return "template";
	}
	@RequestMapping(value="/dashboard/cm/data/_ac/gcd", method=RequestMethod.GET)
	public @ResponseBody List<CampaignPerformanceDataDTO> getCampaignPerformanceData(@RequestParam Map<String, String> params){
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long advertiserId = user.getAdvertiserId();
		List<Campaign> campaigns = campaignService.getCampaigns(advertiserId);
		List<CampaignPerformanceDataDTO> dtos = CampaignPerformanceDataDTO.getListOfDTOs(campaigns);
		return dtos;
	}
	
	@RequestMapping(value="/dashboard/cm/data/_ac/gagd", method=RequestMethod.GET)
	public @ResponseBody List<AdGroupPerformanceDataDTO> getAdGroupPerformanceData(@RequestParam Map<String, String> params){
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long advertiserId = user.getAdvertiserId();
		List<AdGroup> adGroups = new ArrayList<AdGroup>();
		if(params.get("cid") != null){
			Long campaignId = TypeConversion.StringToLong(params.get("cid"));
			adGroups = adGroupService.getAdGroups(campaignId);
		}
		else{
			List<Campaign> campaignTree = campaignService.getCampaignTree(advertiserId);
			for (Campaign campaign : campaignTree) {
				adGroups.addAll(campaign.getAdGroups());
			}
		}
		List<AdGroupPerformanceDataDTO> dtos = AdGroupPerformanceDataDTO.getListOfDTOs(adGroups);
		return dtos;
	}
	
	@RequestMapping(value="/dashboard/cm/data/_ac/gaad", method=RequestMethod.GET)
	public @ResponseBody List<AdPerformanceDataDTO> getAdPerformanceDat(@RequestParam Map<String, String> params){
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long advertiserId = user.getAdvertiserId();
		List<Ad> ads = new ArrayList<Ad>();
		if(params.get("agid") != null){
			Long adGroupId = TypeConversion.StringToLong(params.get("agid"));
			ads = adService.getAds(adGroupId);
		}
		else if(params.get("cid") != null){
			Long campaignId = TypeConversion.StringToLong(params.get("cid"));
			List<AdGroup> adGroups = adGroupService.getAdGroupsTree(campaignId);
			for (AdGroup adGroup : adGroups) {
				ads.addAll(adGroup.getAds());
			}
		}
		else{
			List<Campaign> campaigns = campaignService.getCampaignTree(advertiserId);
			for (Campaign campaign : campaigns) {
				List<AdGroup> adGroups = campaign.getAdGroups();
				for (AdGroup adGroup : adGroups) {
					ads.addAll(adGroup.getAds());
				}
			}
		}
		List<AdPerformanceDataDTO> dtos = AdPerformanceDataDTO.getListOfDTOs(ads);
		return dtos;
	}
	
	/*@RequestMapping(value="/dashboard/cm/data/_ac/{actionId}", method=RequestMethod.GET)
	public @ResponseBody CampaignTree getData(@PathVariable final String actionId, @RequestParam Map<String, String> params){
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long advertiserId = user.getAdvertiserId();
		CampaignTree campaignTree = new CampaignTree();
		if(actionId.equals("gcd")){ //get campaign data
			List<Campaign> campaigns = campaignService.getCampaigns(advertiserId);
			campaignTree.setCampaigns(campaigns);
		}
		else if(actionId.equals("gagd")){ //get adgroup data
			List<Campaign> campaigns;
			if(params.get("cid") != null){
				Long campaignId = TypeConversion.StringToLong(params.get("cid"));
				Campaign campaign = campaignService.getCampaign(campaignId);
				campaigns = new ArrayList<Campaign>();
				campaigns.add(campaign);
			}
			else{
				campaigns = campaignService.getCampaignTree(advertiserId);
			}
			campaignTree.setCampaigns(campaigns);
		}
		else if(actionId.equals("gadd")){ //get ad data
			List<Campaign> campaigns = new ArrayList<Campaign> ();
			if(params.get("agid")!=null){
				Long agid = TypeConversion.StringToLong(params.get("gid"));
				List<Ad> ads = adService.getAds(agid);
				if(ads!=null && ads.size()>0){
					AdGroup adGroup = ads.get(0).getAdGroup();
					Campaign campaign = adGroup.getCampaign();
					campaigns.add(campaign);
				}
			}
			else if(params.get("cid") != null){
				Long campaignId = TypeConversion.StringToLong(params.get("cid"));
				List<AdGroup> adGroupTree = adGroupService.getAdGroups(campaignId);
				if(adGroupTree!=null && adGroupTree.size()>0){
					campaigns.add(adGroupTree.get(0).getCampaign());
				}
			}
			else{
				campaigns = campaignService.getCampaignTree(advertiserId);
			}
			campaignTree.setCampaigns(campaigns);
		}
		return campaignTree;
	}*/
	
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
	
	@RequestMapping(value="/dashboard/cm/addAd", method = RequestMethod.POST)
	public String addAd(@ModelAttribute("imageAdUploadFormDTO") ImageAdUploadFormDTO dto){
		adService.saveImageTextureAd(dto.getAd(), dto.getFile(), dto.getAd().getAdGroup().getAdGroupId());
		return "redirect:/dashboard/cm/ui/_ac/gcmt";
	}
	
}
