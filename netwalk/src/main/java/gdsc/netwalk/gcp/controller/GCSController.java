package gdsc.netwalk.gcp.controller;


import com.google.cloud.storage.BlobInfo;
import gdsc.netwalk.gcp.dto.UploadReqDto;
import gdsc.netwalk.gcp.service.GCSService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class GCSController {

    @Autowired
    private GCSService gcsService;

//    @PostMapping("gcs/download")
//    public ResponseEntity localDownloadFromStorage(@RequestBody DownloadReqDto downloadReqDto){
//        Blob fileFromGCS = gcsService.downloadFileFromGCS(downloadReqDto);
//        return ResponseEntity.ok(fileFromGCS.toString());
//    }

    @PostMapping("gcs/upload")
    public ResponseEntity localUploadToStorage(@RequestBody UploadReqDto uploadReqDto) throws IOException {
        BlobInfo fileFromGCS = gcsService.uploadFileToGCS(uploadReqDto);
        return ResponseEntity.ok(fileFromGCS.toString());
    }
}
