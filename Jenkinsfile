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
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/gs-rest-service-0.1.0.jar', onlyIfSuccessful: true
        }
    }
}
