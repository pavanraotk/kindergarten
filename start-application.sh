#!/usr/bin/env bash
aws s3 cp s3://kindergarten-s3-bucket//application-0.0.1-SNAPSHOT.jar /home/ec2-user/application-0.0.1-SNAPSHOT.jar
sudo yum -y install java-1.8.0
sudo yum -y remove java-1.7.0-openjdk
cd /home/ec2-user/
sudo nohup java -jar application-0.0.1-SNAPSHOT.jar > ec2dep.log