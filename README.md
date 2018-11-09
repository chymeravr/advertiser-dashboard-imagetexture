# Advertiser Dashboard For Image Texture Ads
## Product Description
Advertiser dashboard lets advertisers, aiming to do VR Image Texture Ads, manage ads and budgets. It contains following modules:-
* Registrtaion and login
* Management of ads using campaigns, adgroups and ads
* Bidding, budgeting and scheduling of ads
* Targeting of ads - currently very rudimentary targeting

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

## License

This project is licensed under the MIT License

## Authors
* Sushil Kumar - [Github](https://github.com/sushilmiitb)
