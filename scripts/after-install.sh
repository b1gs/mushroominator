#!/bin/bash


echo "inside a script!!!!"

sudo systemctl stop mushroominator

sudo systemctl status mushroominator

sudo chown -R ubuntu:ubuntu /var/log/mushroominator/*

sudo chown -R ubuntu:ubuntu /home/ubuntu/projects/tmp/mushroominator/*

rm -rf ~/projects/mushroominator
#

## Unzip the deployment package
sudo mkdir ~/projects/mushroominator

sudo chown -R ubuntu:ubuntu ~/projects/mushroominator

sudo rm -rf /home/ubuntu/projects/tmp/mushroominator/scripts

sudo rm -rf /home/ubuntu/projects/tmp/mushroominator/appspec.yml

sudo cp -r /home/ubuntu/projects/tmp/mushroominator/* ~/projects/mushroominator

sudo rm -rf /home/ubuntu/projects/tmp/mushroominator

sudo chown -R ubuntu:ubuntu ~/projects/mushroominator/mushroominator-0.0.1-SNAPSHOT.jar

cd ~/projects/mushroominator

ls -al

echo "Starting java application..."

sudo systemctl start mushroominator
