package co.chimeralabs.advertiser.server.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import co.chimeralabs.advertiser.server.util.RetrieveResources;

@Service
public class CloudStorageAzureImpl implements CloudStorageService{
	
	@Override
	public String getImageTextureAdImageUrl(String resourceIdentifier){
		InputStream inputStream = getClass().getResourceAsStream("/machine/MachineConstants.xml");
		String baseUrl = RetrieveResources.retrieveResourcesAppConatants(inputStream, "azurestoragebaseurl").get(0);
		inputStream = getClass().getResourceAsStream("/machine/MachineConstants.xml");
		String containerName = RetrieveResources.retrieveResourcesAppConatants(inputStream, "azureimagetextureadimagecontainer").get(0);
		
		String url = baseUrl + "/" + containerName + "/" + resourceIdentifier;
		return url;
	}
	
	@Override
	public void storeImageTextureAdImage(byte[] fileBuffer, String filename){
		InputStream inputStream = getClass().getResourceAsStream("/machine/MachineConstants.xml");
		String storageConnectionString = RetrieveResources.retrieveResourcesAppConatants(inputStream, "azurestorageconnectionstring").get(0);
		
		CloudStorageAccount account = null;
		try {
			account = CloudStorageAccount.parse(storageConnectionString);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloudBlobClient serviceClient = account.createCloudBlobClient();
		
		inputStream = getClass().getResourceAsStream("/machine/MachineConstants.xml");
		String containerName = RetrieveResources.retrieveResourcesAppConatants(inputStream, "azureimagetextureadimagecontainer").get(0);
		
		CloudBlobContainer container = null;
		try {
			container = serviceClient.getContainerReference(containerName);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			container.createIfNotExists();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CloudBlockBlob blob = null;
		try {
			blob = container.getBlockBlobReference(filename);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blob.uploadFromByteArray(fileBuffer, 0, fileBuffer.length);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
