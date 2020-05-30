pipeline{
    agent any
    tools {
        maven 'jenkins-maven'
    }
    stages{
        stage('Test'){
            steps{
                echo 'testing...'
                    sh 'mvn test'
                }
            }
        stage('Build'){
            steps{
                echo 'build...'
                sh 'mvn -DskipTests clean install'
            }
        }
        stage('Deploy'){
            steps{
                script {
                    if(GIT_BRANCH == 'master'){
                        echo 'deploying...'
                    }
                }
            }
        }

    }
}