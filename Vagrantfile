# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

$script = <<ENDSCRIPT
  sudo yum install -y epel-release
  sudo yum -y update
  sudo yum install -y net-tools
  sudo yum install -y wget
  sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo
  sudo rpm --import http://pkg.jenkins-ci.org/redhat/jenkins-ci.org.key
  sudo yum install -y jenkins
  sudo yum install -y java-1.8.0-openjdk.x86_64
  sudo systemctl start jenkins.service
  sudo systemctl enable jenkins.service
ENDSCRIPT

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "centos/7"
  config.vm.network "forwarded_port", guest: 8080, host: 8888
  config.vm.provision "shell", inline: $script
end
