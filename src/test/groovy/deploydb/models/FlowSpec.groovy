package deploydb.models

import spock.lang.Specification

class FlowSpec extends Specification {

    def "ensure it can be instantiated"() {
        when:
        def flow = new Flow()

        then:
        flow instanceof Flow
    }
}

class FlowSpecWithArgsSpec extends Specification {

    def "its properties should be correct "() {
        given:
        Artifact artifact = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment preProdDeployment = new Deployment(artifact, "pre-production")
        Deployment prodDeployment = new Deployment(artifact, "production")
        HashSet<Deployment> deployments = new HashSet<Deployment>()
        Flow flow = new Flow()

        preProdDeployment.flow = flow
        prodDeployment.flow = flow
        deployments.add(preProdDeployment)
        deployments.add(prodDeployment)
        String service = "exampleService"

        flow.artifact = artifact
        flow.deployments = deployments
        flow.service = service


        expect:
        flow.artifact == artifact
        flow.deployments == deployments
        flow.service == service
    }

    def "equality passes for same deployments"() {
        Artifact firstArtifact = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment preProdDeployment = new Deployment(firstArtifact, "pre-production")
        Deployment prodDeployment = new Deployment(firstArtifact, "production")
        HashSet<Deployment> firstDeployments = new HashSet<Deployment>()
        Flow firstFlow = new Flow()

        preProdDeployment.flow = firstFlow
        prodDeployment.flow = firstFlow
        firstDeployments.add(preProdDeployment)
        firstDeployments.add(prodDeployment)

        firstFlow.artifact = firstArtifact
        firstFlow.deployments = firstDeployments


        Artifact secondArtifact = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment secondPreProdDeployment = new Deployment(secondArtifact, "pre-production")
        Deployment secondProdDeployment = new Deployment(secondArtifact, "production")
        HashSet<Deployment> secondDeployments = new HashSet<Deployment>()
        Flow secondFlow = new Flow()

        secondPreProdDeployment.flow = secondFlow
        secondProdDeployment.flow = secondFlow
        secondDeployments.add(secondPreProdDeployment)
        secondDeployments.add(secondProdDeployment)

        secondFlow.artifact = secondArtifact
        secondFlow.deployments = secondDeployments

        expect:
        firstFlow == secondFlow
    }

    def "equality fails for different deployments"() {
        Artifact firstArtifact = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment preProdDeployment = new Deployment(firstArtifact, "pre-production")
        Deployment prodDeployment = new Deployment(firstArtifact, "production")
        HashSet<Deployment> firstDeployments = new HashSet<Deployment>()
        Flow firstFlow = new Flow()

        preProdDeployment.flow = firstFlow
        prodDeployment.flow = firstFlow
        firstDeployments.add(preProdDeployment)
        firstDeployments.add(prodDeployment)

        firstFlow.artifact = firstArtifact
        firstFlow.deployments = firstDeployments


        Artifact secondArtifact = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment secondPreProdDeployment = new Deployment(secondArtifact, "dev-integ")
        Deployment secondProdDeployment = new Deployment(secondArtifact, "dev-beta")
        HashSet<Deployment> secondDeployments = new HashSet<Deployment>()
        Flow secondFlow = new Flow()

        secondPreProdDeployment.flow = secondFlow
        secondProdDeployment.flow = secondFlow
        secondDeployments.add(secondPreProdDeployment)
        secondDeployments.add(secondProdDeployment)

        secondFlow.artifact = secondArtifact
        secondFlow.deployments = secondDeployments

        expect:
        firstFlow != secondFlow
    }

    def "equality passes for same service "() {
        String service = "exampleService"
        Flow firstFlow = new Flow()
        Flow secondFlow = new Flow()

        firstFlow.service = service
        secondFlow.service = service

        expect:
        firstFlow == secondFlow
    }

    def "equality fails for different service "() {
        String firstService = "firstService"
        String secondService = "secondService"
        Flow firstFlow = new Flow()
        Flow secondFlow = new Flow()

        firstFlow.service = firstService
        secondFlow.service = secondService

        expect:
        firstFlow != secondFlow
    }

    def "hash compare passes for same deployments"() {
        Artifact firstArtifact = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment preProdDeployment = new Deployment(firstArtifact, "pre-production")
        Deployment prodDeployment = new Deployment(firstArtifact, "production")
        HashSet<Deployment> firstDeployments = new HashSet<Deployment>()
        Flow firstFlow = new Flow()

        preProdDeployment.flow = firstFlow
        prodDeployment.flow = firstFlow
        firstDeployments.add(preProdDeployment)
        firstDeployments.add(prodDeployment)

        firstFlow.artifact = firstArtifact
        firstFlow.deployments = firstDeployments

        Artifact secondArtifact = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment secondPreProdDeployment = new Deployment(secondArtifact, "pre-production")
        Deployment secondProdDeployment = new Deployment(secondArtifact, "production")
        HashSet<Deployment> secondDeployments = new HashSet<Deployment>()
        Flow secondFlow = new Flow()

        secondPreProdDeployment.flow = secondFlow
        secondProdDeployment.flow = secondFlow
        secondDeployments.add(secondPreProdDeployment)
        secondDeployments.add(secondProdDeployment)

        secondFlow.artifact = secondArtifact
        secondFlow.deployments = secondDeployments

        expect:
        firstFlow.hashCode() == secondFlow. hashCode()
    }

    def "hash code fails for different deployments"() {
        Artifact firstArtifact = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment preProdDeployment = new Deployment(firstArtifact, "pre-production")
        Deployment prodDeployment = new Deployment(firstArtifact, "production")
        HashSet<Deployment> firstDeployments = new HashSet<Deployment>()
        Flow firstFlow = new Flow()

        preProdDeployment.flow = firstFlow
        prodDeployment.flow = firstFlow
        firstDeployments.add(preProdDeployment)
        firstDeployments.add(prodDeployment)

        firstFlow.artifact = firstArtifact
        firstFlow.deployments = firstDeployments


        Artifact secondArtifact = new Artifact('spock.group',
                'spock-test-name',
                'v1.0.0',
                'http://example.com/cucumber.jar')
        Deployment secondPreProdDeployment = new Deployment(secondArtifact, "dev-integ")
        Deployment secondProdDeployment = new Deployment(secondArtifact, "dev-beta")
        HashSet<Deployment> secondDeployments = new HashSet<Deployment>()
        Flow secondFlow = new Flow()

        secondPreProdDeployment.flow = secondFlow
        secondProdDeployment.flow = secondFlow
        secondDeployments.add(secondPreProdDeployment)
        secondDeployments.add(secondProdDeployment)

        secondFlow.artifact = secondArtifact
        secondFlow.deployments = secondDeployments

        expect:
        firstFlow.hashCode() != secondFlow.hashCode()
    }

    def "hash code passes for same service "() {
        String service = "exampleService"
        Flow firstFlow = new Flow()
        Flow secondFlow = new Flow()

        firstFlow.service = service
        secondFlow.service = service

        expect:
        firstFlow.hashCode() == secondFlow.hashCode()
    }

    def "hash code fails for different service "() {
        String firstService = "firstService"
        String secondService = "secondService"
        Flow firstFlow = new Flow()
        Flow secondFlow = new Flow()

        firstFlow.service = firstService
        secondFlow.service = secondService

        expect:
        firstFlow.hashCode() != secondFlow.hashCode()
    }

}
