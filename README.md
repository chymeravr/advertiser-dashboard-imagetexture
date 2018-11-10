
# Advertiser Dashboard For Image Texture Ads
## Product Description
Advertiser dashboard lets advertisers, aiming to do VR Image Texture Ads, manage ads and budgets. It contains following modules
* Registrtaion and login
* Management of ads using campaigns, adgroups and ads
* Bidding, budgeting and scheduling of ads
* Targeting of ads - currently very rudimentary targeting
* APIs for adserver to query all ads periodically

## Getting started
### Pre-requisites
*  install maven 2+ ```sudo apt-get install maven```
* install java 6+
*  install git
* install mysql 5.5+ ```sudo apt-get install mysql-server```
* install eclipse jee
* [install tomcat 7+](https://www.tecmint.com/install-apache-tomcat-in-centos/)

### Setting up local environment
* Update user.properties
*  In mysql, create schema ```create schema advertiserdb;```
* Update db.properties - Make ```hibernate.hbm2ddl.auto=create``` ---- convert it back to validate once tables are created
* Build the module ```mvn clean package```
* Start the server ```sudo $CATALINA_HOME/bin/startup.sh```
* Do ```hibernate.hbm2ddl.auto=validate``` and rebuild and restart the server
* Pre-populate db
```sql
INSERT INTO security_group (name) VALUES ("advertiser");
INSERT INTO security_group_authority (authority, security_group_id) VALUES ("ROLE_ADVERTISER", 1);
INSERT INTO texture_image_format (width, height) VALUES(940,788);
INSERT INTO texture_image_format (width, height) VALUES(2245,1587);
INSERT INTO texture_image_format (width, height) VALUES(640,480);
INSERT INTO texture_image_format (width, height) VALUES(1024,512);
```
* For saving files of ad image create in webapp the folder adnetwork/adresources/textureimages. 
* Webapp will be running at [Advertiser Dashboard](http://localhost:8080/advertiserdb)

## APIs for adserver to consume
### Get all ads
Get all the ads that are eligible for bidding.

**URL** : `/api/ads`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**

Returns a list of ads that are eligible for bidding.

```json
[
	{
		ads: 
		[	
			{
				advertiserId:"1234",
				adId:"2343223",
				adResourceUrl:"http://storagedeveloperchimera.blob.core.windows.net/image-texture-ad-images/axssie23dssc.jpg",
				errorMsg:""
			}
		],
		errorMsg: ""
	},
	{
		ads: 
		[	
			{
				advertiserId:"1235",
				adId:"2343226",
				adResourceUrl:"http://storagedeveloperchimera.blob.core.windows.net/image-texture-ad-images/axssie23asdfsc.jpg",
				errorMsg:""
			}
		],
		errorMsg: ""
	}
]
```

## Notes

* If the User does not have a `UserInfo` instance when requested then one will
  be created for them.

## License

This project is licensed under the MIT License

## Authors
* Sushil Kumar - [Github](https://github.com/sushilmiitb)
