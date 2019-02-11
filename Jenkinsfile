pipeline {
  agent any
  tools {
  jdk 'java'
  maven 'maven'
   }
  stages {
        stage('Build') { 
            steps {
               logstash {
                 try {
                bat 'mvn -B -DskipTests clean package' 
                   currentBuild.result = 'SUCCESS'
                 } catch (Exception err) {
                   currentBuild.result = 'FAILURE'
                            }
                 echo "RESULT: ${currentBuild.result}"

                   
               } 
              }
        }
        stage('Test') { 
            steps { 
              logstash {
                bat 'mvn test' 
                 }
            }
            post {
              
                always {
                  
                    junit 'target/surefire-reports/*.xml'   
                           
                  logstashSend failBuild: true, maxLines: 1000
                 
                }
            }
        }
   
       stage('sonarcube analysis') {
            steps {
              logstash {
               withSonarQubeEnv('sonarserver'){
                 bat 'mvn sonar:sonar' 
               
                           }
                }
              }
            }
       stage("Quality Gate") {
            steps {
              logstash {  
              timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    // Requires SonarQube Scanner for Jenkins 2.7+
                    waitForQualityGate abortPipeline: true
                        
               }
              }
            }
         }
    stage(hygieia){
      steps{
      hygieiaArtifactPublishStep artifactDirectory: '/target', artifactGroup: 'artifact.dev.product', artifactName: '*.jar', artifactVersion: '1.0'  
      hygieiaSonarPublishStep ceQueryIntervalInSeconds: '10', ceQueryMaxAttempts: '30'
      hygieiaDeployPublishStep applicationName: 'myApp', artifactDirectory: '/target', artifactGroup: 'artifact.dev.product', artifactName: '*.jar', artifactVersion: '1.0', buildStatus: 'Success', environmentName: 'env09090'
      
      }
    }
     
     stage('Ready API(soap UI)'){
       steps{
         echo """ 
            FOR RUNNING THIS STAGE YOU NEED AN AGENT HAVING READY API TOOL.
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
            logstash {
           echo """
             xldCreatePackage artifactsPath: 'build/libs',
             manifestPath: 'deployit-manifest.xml', 
             darPath: '$JOB_NAME-$BUILD_NUMBER.0.dar' """
                      
              } 
             }
            }
         stage('Publish') {
           steps{
             logstash {
          echo """
              xldPublishPackage darPath: 'path-of-dar', 
              serverCredentials: 'admin_xldeloy' """ 
          
              }
             }    
           }
         stage('Deploy') {
           steps{
             logstash {
           echo """
             xldDeploy environmentId: 'Environments/env-of-xldeploy', 
             packageId: 'Applications/name-of-application', 
             serverCredentials: 'admin_xldeloy' """
            
             }             
           }
         }
       }        
    }

  }
post {
  try {
    echo """ 
             ONLY PRINT
             mail to: 'kartik3588@gmail.com',
             """
      currentBuild.result = 'SUCCESS'
       logstashSend failBuild: true, maxLines: 1000       
     }  catch (Exception err) {
        mail to: 'kartik3588@gmail.com',
             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}"
      currentBuild.result = 'FAILURE'
     logstashSend failBuild: true, maxLines: 1000

    currentBuild.result = 'FAILURE'
    }
    echo "RESULT: ${currentBuild.result}"

    
 }

}
