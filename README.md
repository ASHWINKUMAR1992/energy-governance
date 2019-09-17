# energy-governance
## Developer setup

### Prerequisite

- Download Postgresql on your machine. Do not use HomeBrew to install.
You can download it from https://www.postgresql.org/download/macosx/. 
   - If it has not prompted for the port, it installs by default on 5432. Please make a note of the 	username and password during the installation process. 
   	
- Install a DB Viewer tool. We recommend Dbeaver (https://dbeaver.io/download/). You  should use the username and password from the first step here to connect to the 			database. Some of the other tools that you can use are pgadmin, sqlbrowser etc.
- URL to connect to local database is already in place in `application.proerties`. You can check this entry in your properties to confirm.

### Development

#### Command line

1. Navigate to the project repo in terminal and run `mvn clean install` or you can directly complete this step from  repo.
2. Run `mvn spring-boot:run`.
3. You should see the energy-governance application up and running on port localhost:8080.

#### IDE
1. Navigate to the project repo in terminal and run `mvn clean install` or you can directly complete this step from  repo.
2. `RunAs Maven clean` then `Maven install`
3. You can run the application in 2 ways.  
   1. Right click on the project and `RunAs MavenBuild`.  
   2. Right click on the project `RunAs->RunConfiguration`. Add new run configuration under spring boot app. 
4. Select project as energy-governance, Main type as "com.ashwin.energygovernance.EnergyGovernanceApplication" and hit `Run`. You can save this configuartion as Customer-Local for easy access in future.
5. You should see the energy-governance application up and running on port localhost:8080.
