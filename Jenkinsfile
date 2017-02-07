node {
   def mvnHome
   def scannerHome
   properties([[$class: 'GitLabConnectionProperty', gitLabConnection: ''], [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], pipelineTriggers([[$class: 'GitHubPRTrigger', events: [[$class: 'GitHubPROpenEvent']], spec: '* * * * *', triggerMode: 'CRON']])])
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'ca9b112d-19c3-491c-8e6d-23ec20cc5290', refspec: '+refs/pull/*:refs/remotes/origin/pr/*', url: 'https://github.com/amruthapbhat/java-maven-junit-helloworld']]])
      
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'Maven'
      scannerHome = tool 'Sonar'
   }
         
   stage('Build') {
      // Run the maven build
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean install/)      
   }
   
   stage ('Sonar Analysis') {
   //Running Sonar Analysis
   withSonarQubeEnv {
   bat(/"${scannerHome}\bin\sonar-scanner" -Dsonar.projectKey=java-maven-junit-helloworld -Dsonar.sources=./)
     }
   }   
}
