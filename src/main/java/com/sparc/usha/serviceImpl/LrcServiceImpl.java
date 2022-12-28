package com.sparc.usha.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sparc.usha.customException.FileStorageException;
import com.sparc.usha.entity.LRCapplicationEntity;
import com.sparc.usha.entity.UshaSurveyEntity;
import com.sparc.usha.repository.LrcRepository;
import com.sparc.usha.repository.StatusRepository;
import com.sparc.usha.repository.UshaSurveyRepository;
import com.sparc.usha.request.LrcRequest;
import com.sparc.usha.response.LrcResponse;
import com.sparc.usha.service.FileUploadService;
import com.sparc.usha.service.LrcService;

@Service
public class LrcServiceImpl implements LrcService {
	@Value("${file.upload-dir}")
	private String filelocation;
	@Autowired
	LrcRepository lrcRepo;
	@Autowired
	UshaSurveyRepository ushaSurveyRepo;
	@Autowired
	StatusRepository statusRepo;
	@Autowired
	FileUploadService fileUploadService;

	@Override
	public List<LrcResponse> getAllLrcDetailsByUshaSurveyId(Integer ushaSurveyId) {
		try {
			Optional<LRCapplicationEntity> opt = lrcRepo.getAllByUshaSurveyId(ushaSurveyId);
			List<LrcResponse> list = new ArrayList<>();
			if (opt.isPresent()) {
				opt.stream().forEach(x -> {
					LrcResponse response = new LrcResponse();
					response.setUshaSurveyId(ushaSurveyId);
					response.setLrcApplicantionId(x.getLrcApplicantionId());
					response.setPlotOccupyingDate(x.getPlotOccupyingDate());
					response.setNorthDirection(x.getNorthDirection());
					response.setEastDirection(x.getEastDirection());
					response.setWestDirection(x.getWestDirection());
					response.setSouthDirection(x.getSouthDirection());
					response.setApplicantPhoto(x.getApplicantPhoto());
					response.setSpousePhoto(x.getSpousePhoto());
					response.setApplicantFingerprint(x.getApplicantFingerprint());
					response.setSpouseFingerprint(x.getSpouseFingerprint());
					response.setActiveStatus(x.getActiveStatus());
					list.add(response);

				});

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public String insertLrcDetails(LrcRequest request) {
		String msg = "";
		LRCapplicationEntity lrcData = new LRCapplicationEntity();
		try {
			if (request != null) {
				Optional<UshaSurveyEntity> opt = ushaSurveyRepo.findById(request.getUshaSurveyId());
				if (opt.isPresent()) {
					UshaSurveyEntity data = opt.get();
					data.setStatus(statusRepo.findById(request.getStatusId()).get());
					data.setUpdated_on(LocalDate.now());
					ushaSurveyRepo.save(data);
				}
				lrcData.setUshaSurveyId(ushaSurveyRepo.findById(request.getUshaSurveyId()).get());
				lrcData.setPlotOccupyingDate(request.getPlotOccupyingDate());
				lrcData.setEastDirection(request.getEastDirection());
				lrcData.setWestDirection(request.getWestDirection());
				lrcData.setNorthDirection(request.getNorthDirection());
				lrcData.setSouthDirection(request.getSouthDirection());
				lrcData.setCreatedOn(LocalDate.now());
				// lrcData.setActiveStatus(request.getActiveStatus());
				MultipartFile applicantPhoto = request.getApplicantPhoto();
				MultipartFile spousePhoto = request.getSpousePhoto();
				MultipartFile applicantFingerprint = request.getApplicantFingerprint();
				MultipartFile spouseFingerprint = request.getSpouseFingerprint();
				if (!applicantPhoto.isEmpty()) {
					String fileUploadPath = fileUploadService.fileUpload(applicantPhoto);
					lrcData.setApplicantPhoto(fileUploadPath);
				}
				if (!spousePhoto.isEmpty()) {
					String fileUploadPath = fileUploadService.fileUpload(spousePhoto);
					lrcData.setSpousePhoto(fileUploadPath);
				}
				if (!applicantFingerprint.isEmpty()) {
					String fileUploadPath = fileUploadService.fileUpload(applicantFingerprint);
					lrcData.setApplicantFingerprint(fileUploadPath);
				}
				if (!spouseFingerprint.isEmpty()) {
					String fileUploadPath = fileUploadService.fileUpload(spouseFingerprint);
					lrcData.setSpouseFingerprint(fileUploadPath);
					}		
				LRCapplicationEntity save = lrcRepo.save(lrcData);
				if (save != null)
					msg = "success";
				else
					msg = "failed";
			}
			return msg;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public String updateLrcDetails(LrcRequest request) {
		String msg = "";
		try{
			Optional<LRCapplicationEntity> opt1 = lrcRepo.findById(request.getLrcApplicantionId());
			// LRCapplicationEntity lrcData = new LRCapplicationEntity();
			if (request != null) {
				Optional<UshaSurveyEntity> opt = ushaSurveyRepo.findById(request.getUshaSurveyId());
				if (opt.isPresent()) {
					UshaSurveyEntity data = opt.get();
					//data.setStatus(statusRepo.findById(request.getStatusId()).get());
					data.setUpdated_on(LocalDate.now());
					ushaSurveyRepo.save(data);
				}
				if (opt1.isPresent()) {
					LRCapplicationEntity lrcData = opt1.get();
					lrcData.setUshaSurveyId(ushaSurveyRepo.findById(request.getUshaSurveyId()).get());
					lrcData.setPlotOccupyingDate(request.getPlotOccupyingDate());
					lrcData.setEastDirection(request.getEastDirection());
					lrcData.setWestDirection(request.getWestDirection());
					lrcData.setNorthDirection(request.getNorthDirection());
					lrcData.setSouthDirection(request.getSouthDirection());
					lrcData.setUpdated_on(LocalDate.now());
					// lrcData.setActiveStatus(request.getActiveStatus());
					MultipartFile applicantPhoto = request.getApplicantPhoto();
					MultipartFile spousePhoto = request.getSpousePhoto();
					MultipartFile applicantFingerprint = request.getApplicantFingerprint();
					MultipartFile spouseFingerprint = request.getSpouseFingerprint();
					if (!applicantPhoto.isEmpty()) {
						String fileUploadPath = fileUploadService.fileUpload(applicantPhoto);
						lrcData.setApplicantPhoto(fileUploadPath);
					}
					if (!spousePhoto.isEmpty()) {
						String fileUploadPath = fileUploadService.fileUpload(spousePhoto);
						lrcData.setSpousePhoto(fileUploadPath);
					}
					if (!applicantFingerprint.isEmpty()) {
						String fileUploadPath = fileUploadService.fileUpload(applicantFingerprint);
						lrcData.setApplicantFingerprint(fileUploadPath);
					}
					if (!spouseFingerprint.isEmpty()) {
						String fileUploadPath = fileUploadService.fileUpload(spouseFingerprint);
						lrcData.setSpouseFingerprint(fileUploadPath);
					}
					LRCapplicationEntity save = lrcRepo.save(lrcData);
					if (save != null)
						msg = "success";
					else
						msg = "failed";
				}
			}
		return msg;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
		
	}
}
