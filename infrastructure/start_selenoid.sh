#! /bin/sh
docker pull selenoid/vnc_chrome:85.0
docker pull selenoid/vnc_chrome:86.0
docker pull selenoid/vnc_firefox:78.0
docker pull selenoid/vnc_firefox:77.0
docker pull selenoid/video-recorder:latest-release
docker-compose up -d
cd ../
mvn clean test -DuseSelenoid=true
