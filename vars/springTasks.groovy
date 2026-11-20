
def verifySource() {
    sh '''
        set -eu
        test -s pom.xml
        echo "SHARED LIBRARY SOURCE CHECK PASSED"
    '''
}

def buildWar() {
    sh '''
        set -eu
        mvn -B -Djdk.version=1.8 \
          -Dmaven.compiler.source=8 \
          -Dmaven.compiler.target=8 \
          clean test \
          org.apache.maven.plugins:maven-war-plugin:3.4.0:war

        echo "SHARED LIBRARY MAVEN BUILD PASSED"
    '''
}

def archiveWar() {
    sh '''
        set -eu
        ls -lh target/*.war
        echo "SHARED LIBRARY WAR VERIFIED"
    '''

    archiveArtifacts artifacts: 'target/*.war',
                     fingerprint: true
}
