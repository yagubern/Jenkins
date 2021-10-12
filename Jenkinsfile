pipeline {
agent { label 'docker-slave' }
    stages {
        stage('Checkout code') {
            steps {
                checkout scm
            }
        }
        stage('Check maven version') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/gs-rest-service-0.1.0.jar', onlyIfSuccessful: true
        }
    }
}
