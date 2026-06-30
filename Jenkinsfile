pipeline {
    agent any

    stages {
        stage('Verify Workspace') {
            steps {
                echo "Code is already checked out by Jenkins"
                bat 'dir'
            }
        }

        stage('Check Java and Maven') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run TestNG Tests') {
            steps {
                bat 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed'
        }

        success {
            echo 'Build and tests executed successfully'
        }

        failure {
            echo 'Build failed. Please check Maven/JDK/certificate configuration.'
        }
    }
}
