pipeline{
    agent any
    tools {
        maven 'jenkins-maven'
    }
    stages{
        stage('Checkout'){
            steps{
                  echo "checkout ${GIT_BRANCH}"
            }
        }
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
                echo 'deploying...'
            }
        }
    }
}