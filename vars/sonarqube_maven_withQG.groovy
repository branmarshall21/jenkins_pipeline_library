#!/usr/bin/env groovy

def call() {
node {
   stage('Sonar Scan') {
     def scannerHome = tool 'SonarScanner-3.0.3.778'
     withSonarQubeEnv('My SonarQube Server'){
     // requires SonarQube Scanner for Maven 3.2+
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
     }
   }

   // No need to occupy a node
  stage("Quality Gate"){
    timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
      def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
      if (qg.status != 'OK') {
        error "Pipeline aborted due to quality gate failure: ${qg.status}"
        }
      }
    }
}
}
