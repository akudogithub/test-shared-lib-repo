def call(String repoUrl){
  pipeline{
    agent any
    stages{
        stage('git-clone'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github-id', url: 'https://github.com/akudogithub/jenkins-test.git']])
            }
        }
        stage('cpu-check'){
            steps{
                sh 'lscpu'
            }
        }
        stage('security-check'){
            steps{
                sh 'bash /var/lib/jenkins/workspace/jenkins-test-pipeline/security.sh'
            }
        }
        stage('system-check'){
            steps{
                sh 'lsblk'
            }
        }
        stage('ram-check'){
            steps{
                sh 'free -g'
                sh 'free -m'
            }
        }
        stage('regression-test'){
            parallel{
                stage('test-a'){
                    steps{
                        sh 'date'
                    }
                }
                stage('test-b'){
                    steps{
                        sh 'cal'
                    }
                }
            }
        }
        stage('check-user'){
            steps{
                sh 'lsblk'
            }
        }
    }
}
