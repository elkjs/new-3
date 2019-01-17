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


post {
    failure {
        mail to: 'kartik3588@gmail.com',
             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}"
    }
}
}
