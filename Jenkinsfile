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
              echo " withSonarQubeEnv('My SonarQube server name')  bat 'mvn sonar:sonar "
        
              }
            }
           stage("Quality Gate") {
            steps {
               echo " something condition if pass or fail and also make WEBHOOK from sonarqube server "
                }
            }
       }
   }
     stage('xldeploy') {
          parallel {   
        stage('Package') {  
           echo "xldCreatePackage artifactsPath: 'build/libs', manifestPath: 'deployit-manifest.xml', darPath: '$JOB_NAME-$BUILD_NUMBER.0.dar' " 
              }  
         stage('Publish') {  
          echo " xldPublishPackage serverCredentials: '<user_name>', darPath: '$JOB_NAME-$BUILD_NUMBER.0.dar' "
             }    
         stage('Deploy') {  
           echo " xldDeploy serverCredentials: '<user_name>', environmentId: 'Environments/Dev', packageId: 'Applications/<project_name>/$BUILD_NUMBER.0' "
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
}
}
