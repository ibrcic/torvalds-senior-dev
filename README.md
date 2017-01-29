# Torvalds Senior Development Project

Introduction
------------

This is a private repository to host all the code related to team Torvalds senior development project. For the sake of simplicity all three parts of this projects are located in single repository which has certain drawbacks and advantages.

The project is divided into three main sub modules that are all in their respective subdirectories. The sub projects are as following:

 - [android](https://github.com/ibrcic/torvalds-senior-dev/tree/master/android)
 - [angular-frontend](https://github.com/ibrcic/torvalds-senior-dev/tree/master/angular-frontend)
 - [java-backend](https://github.com/ibrcic/torvalds-senior-dev/tree/master/java-backend)

Working with GIT
----------------

 **Cloning the whole project**

 - Make sure that GIT is installed on your machine - [download git](https://git-scm.com/downloads).
 - Open terminal and cd into the location at which you want the project to be located at.
 - Type `git clone https://github.com/ibrcic/torvalds-senior-dev.git`
 - The repository will now be cloned locally.

**Committing and pushing the code**

 - From terminal, go to root of project.
 - If there are new files type `git add *` to add them all.
 - To commit changes type `git commit -m "commit message"` and replace the message with appropriate one.
 - If you want to push to github, type `git push`

**Updating files**

 - From terminal go to project root directory
 - Type in `git pull`

**Tips**

 - If you do not want to use terminal there are many great programs to work with git repositories. [SourceTree](https://www.sourcetreeapp.com/) is already mentioned and available for both Mac and Windows but others are just a Google away.
 - Commit often and leave a meaningful commit message.
 - Type either refactor: feat: or bug: at the beginning of your commit message so that it is easily distinguishable was this commit refactor, a new feature or bug fix.
 - **Do not** push code that breaks builds.
 - Try to avoid conflict by not working on the same code as someone else.
 - Use .gitignore file to ignore IDE specific files such as .idea files if working with InteliJ.


TODO
----

 - Write a meaningful README.md files for all thre subfolders
