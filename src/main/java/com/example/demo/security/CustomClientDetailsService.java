package com.example.demo.security;

import com.example.demo.repository.ClientRepository;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomClientDetailsService implements ClientDetailsService{

    private final ClientRepository clientRepository;

    public CustomClientDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientRepository.findByClientId(clientId)
                .map(cc -> {

                    BaseClientDetails clientDetails = new BaseClientDetails();
                    clientDetails.setClientId(cc.getClientId());
                    clientDetails.setAuthorizedGrantTypes(Arrays.asList("client_credentials", "password"));
                    clientDetails.setClientSecret(cc.getSecret());
                    clientDetails.setScope(Arrays.asList("resource-server-read", "resource-server-write"));
                    return clientDetails;


                })
                .orElseThrow(() -> new RuntimeException(String.format("Client with client_id %s not found", clientId)));
    }
}
