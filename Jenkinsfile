pipeline {
  agent any
  tools {
  jdk 'java'
  maven 'maven'
   }
  stages {
        stage('Build') { 
            steps {
                
                bat 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') { 
            steps {
                bat 'mvn test' 
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        }
       stage('sonarcube & quality gate') {
          parallel {
           stage('build && SonarQube analysis') {
            steps {
              echo """ withSonarQubeEnv('My SonarQube server name'){
                 bat 'mvn sonar:sonar'
                   } """
        
              }
            }
           stage("Quality Gate") {
            steps {
               echo " something condition if pass or fail and also make WEBHOOK from sonarqube server "
                }
            }
       }
   }
     stage('Ready API(soap UI)'){
       steps{
         echo """
          SoapUIPro environment: '',
            pathToProjectFile: 'C:\\Default-SoapUI-Pro-Project-readyapi-project.xml',
            pathToTestrunner: 'C:\\Program Files\\SmartBear\\ReadyAPI-2.5.0\\bin',
            projectPassword: '',
            testCase: 'REST TestCase',
            testSuite: 'TestSuite' """  
             
       }
          
          }
     stage('xldeploy') {
      parallel {   
        stage('Package') {  
          steps{
           echo """
             xldCreatePackage artifactsPath: 'build/libs',
             manifestPath: 'deployit-manifest.xml', 
             darPath: '$JOB_NAME-$BUILD_NUMBER.0.dar' """
              } 
            }
         stage('Publish') {
           steps{
          echo """
              xldPublishPackage darPath: 'path-of-dar', 
              serverCredentials: 'admin_xldeloy' """ 
             
             }    
           }
         stage('Deploy') {
           steps{
           echo """
             xldDeploy environmentId: 'Environments/env-of-xldeploy', 
             packageId: 'Applications/name-of-application', 
             serverCredentials: 'admin_xldeloy' """
                             
           }
         } 
       }        
    }

  }
post {
    failure {
        mail to: 'kartik3588@gmail.com',
             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}"
    }
    success {
       echo """ 
             mail to: 'kartik3588@gmail.com',
             """
             
    }
  }
}
