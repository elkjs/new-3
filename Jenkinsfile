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
    }

}
