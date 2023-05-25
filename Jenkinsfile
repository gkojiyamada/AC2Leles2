pipeline {
  agent any
  
  tools {
    maven "M3"
  }
  stages {
    stage('Build') {
      steps {
        echo "Iniciando deploy"
        git 'https://github.com/gkojiyamada/AC2Leles2.git'
        bat 'mvn clean package'
        bat 'docker system prune -a --volumes -f'
        bat 'docker build . --tag integration/integration-prod'
      }
      
      post {
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts 'target/*.jar'
          step( [ $class: 'JacocoPublisher' ] )
        }
      }
    }
    stage('Deploy') {
      steps {
        echo "Iniciando deploy"
      }
    }
  }

}
