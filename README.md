# Welcome to my Zendesk Ticket Viewer Repo

### To install this application


####Through IDE
    Simply clone this repo and initialize a new project from existing source in your IDE.
    Then simply click the run button in your IDE and this application should start up on
    port 8080. Navigate to localhost:8080 to begin using the application. If port 8080 is
    in use by another process on your computer simply change the port to an available one
    throught the application.properties configuration file.
####In Terminal
    Simply clone this repository and navigate in to the root of the folder, then
    execute this command:

        mvn clean package && cd target && java -jar -Dspring.profiles.active=local ticketviewer-0.0.1-SNAPSHOT.jar

    this should start the application on port 8080 or the port you have specified in the
    applications.properties file.

###To use this application
    1. Navigate to localhost:{port_number}

    2. Fill out the information requested on the page and hit submit
        - This should take you to the page where you can view all tickets
        - If you would like to simplify the process you can override these properties
    in the application.properties file
            zendesk.api.baseurl=https://zccnewwenchy.zendesk.com
            zendesk.api.auth.email=wcdutreuil@gmail.com
            zendesk.api.auth.password=******
    and then you will be able to navigate directly to localhost:{port_number}/tickets without a problem.
            -- As an additional note, I recommend specifying either a
                application-local.properties or a application-local.yml
            to overide the password or all of those configurations, because these two files
            are automatically ignored by git.

    3. Once you arrive at the /tickets endpoint, you can page through the tickets
        or you can click to expand a ticket and then click view to view that tickets
        specific page.

    4. Once on a ticket specific page, you can either click go back to Go Back to the
        page you were on, Or click View Raw to view the Json Payload for that specific ticket

    5. If you ever land on the /myError page simply click Go Home to go back to the index page.