package gdsc.netwalk.gcp.service;


import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import gdsc.netwalk.gcp.dto.UploadReqDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class GCSService {
    /*
    * @Autowired 해야하는가?
    * -> @Component로 등록되지 않았기에 Autowired되지 않는다.(?)
    * */
    private Storage storage;

//    public Blob downloadFileFromGCS(String bucketName, String downloadFileName, String localFileLocation) {
//        Blob blob = storage.get(bucketName, downloadFileName);
//        blob.downloadTo(Paths.get(localFileLocation));
//        return blob;
//    }

    public BlobInfo uploadFileToGCS(UploadReqDto uploadReqDto) throws IOException {

        BlobInfo blobInfo =storage.create(
                BlobInfo.newBuilder(uploadReqDto.getBucketName(), uploadReqDto.getUploadFileName())
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
                        .build(),
                new FileInputStream(uploadReqDto.getLocalFileLocation()));

        return blobInfo;
    }


}
