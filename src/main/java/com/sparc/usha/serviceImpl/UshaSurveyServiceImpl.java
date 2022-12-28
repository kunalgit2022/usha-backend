package com.sparc.usha.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sparc.usha.entity.HouseholdEntity;
import com.sparc.usha.entity.IdentitytProofEntity;
import com.sparc.usha.entity.LRCapplicationEntity;
import com.sparc.usha.entity.UshaSurveyEntity;
import com.sparc.usha.repository.HouseHoldRepository;
import com.sparc.usha.repository.IdentitytProofRepository;
import com.sparc.usha.repository.LrcRepository;
import com.sparc.usha.repository.MunicipalityRepository;
import com.sparc.usha.repository.SlumRepository;
import com.sparc.usha.repository.StatusRepository;
import com.sparc.usha.repository.UserRepository;
import com.sparc.usha.repository.UshaSurveyRepository;
import com.sparc.usha.request.UshaSurveyRequest;
import com.sparc.usha.response.HouseholdResponse;
import com.sparc.usha.response.IdentityProofResponse;
import com.sparc.usha.response.UshaSurveyResponse;
import com.sparc.usha.service.FileUploadService;
import com.sparc.usha.service.UshaSurveyService;

@Service
public class UshaSurveyServiceImpl implements UshaSurveyService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UshaSurveyRepository ushaSurveyRepo;
	@Autowired
	MunicipalityRepository municipalityRepo;
	@Autowired
	SlumRepository slumRepo;
	@Autowired
	StatusRepository statusRepo;
	@Autowired
	IdentitytProofRepository identityProofRepo;
	@Autowired
	HouseHoldRepository houseHoldRepo;
	@Autowired
	LrcRepository lrcRepo;
	IdentitytProofRepository identitytProofRepo;
	@Autowired
	FileUploadService fileUploadService;

	@Value("${file.upload-dir}")
	private String filelocation;

	/**
	 * @author prasanjit
	 *
	 */
	@SuppressWarnings("unused")
	@Override
	public String insertUshaSurveyData(UshaSurveyRequest request) {
		String msg = "";

		UshaSurveyEntity surveyData = new UshaSurveyEntity();
		if (request != null) {
			surveyData.setMunicipalityId(municipalityRepo.findById(request.getMunicipalityId()).get());
			surveyData.setWardNo(request.getWardNo());
			surveyData.setSlumType(slumRepo.findById(request.getSlumType()).get());
			surveyData.setStreetLane(request.getStreetLane());
			surveyData.setHouseNo(request.getHouseNo());
			surveyData.setApplicantName(request.getApplicantName());
			surveyData.setApplicantMobile(request.getApplicantMobile());
			surveyData.setApplicantGender(request.getApplicantGender());
			surveyData.setParentSpouseName(request.getParentSpouseName());
			surveyData.setParentSpouseMobile(request.getParentSpouseMobile());
			surveyData.setFamilyAnualIncome(request.getFamilyAnualIncome());
			surveyData.setAnualIncomeProof(request.getAnualIncomeProof());
			surveyData.setEwsProof(request.getEwsProof());
			surveyData.setEwsProofType(request.getEwsProofType());
			surveyData.setCastCategory(request.getCastCategory());
			surveyData.setCastCertificate(request.getCastCertificate());
			surveyData.setPwd(request.getPwd());
			surveyData.setPwdCertificate(request.getPwdCertificate());
			surveyData.setReligion(request.getReligion());
			surveyData.setApplicantIdProofType(identityProofRepo.findById(request.getApplicantIdProofType()).get());
			surveyData.setApplicantIdProofNo(request.getApplicantIdProofNo());
			surveyData.setApplicantIdProofIssueDate(LocalDate.parse(request.getApplicantIdProofIssueDate()));
			surveyData.setApplicantResdProofType(identityProofRepo.findById(request.getApplicantResdProofType()).get());
			surveyData.setApplicantResdProofNo(request.getApplicantResdProofNo());
			surveyData.setApplicantResdProofIssueDate(LocalDate.parse(request.getApplicantResdProofIssueDate()));
			surveyData.setSpouseIdProofType(identityProofRepo.findById(request.getSpouseIdProofType()).get());
			surveyData.setSpouseIdProofNo(request.getSpouseIdProofNo());
			surveyData.setSpouseIdProofIssueDate(LocalDate.parse(request.getSpouseIdProofIssueDate()));
			surveyData.setWallType(request.getWallType());
			surveyData.setRoofType(request.getRoofType());
			surveyData.setHouseHaveToilet(request.getHouseHaveToilet());
			surveyData.setDwellingAccessToPuccaPaver(request.getDwellingAccessToPuccaPaver());
			surveyData.setLandOwnershipInAnyUlb(request.getLandOwnershipInAnyUlb());
			surveyData.setLandOwnershipInSameUlb(request.getLandOwnershipInSameUlb());
			surveyData.setServeyorId(userRepo.findById(request.getServeyorId()).get());
			// surveyData.setAssigneeId(userRepo.findById(request.getAssigneeId()).get());
			// surveyData.setAssignDate(LocalDate.now());
			surveyData.setReportedDate(LocalDate.now());
			surveyData.setStatus(statusRepo.findById(request.getStatus()).get());
			surveyData.setActiveStatus(request.getActiveStatus());
			surveyData.setCreatedOn(LocalDate.now());
			surveyData.setUpdated_on(LocalDate.now());
			MultipartFile idProofImage = request.getApplicantIdProofImage();
			MultipartFile resdProofImage = request.getApplicantResdProofImage();
			MultipartFile spouseidProofImage = request.getSpouseidProofImage();
			MultipartFile ewsProofCertificate = request.getEwsProofCertificate();
			MultipartFile castProofCertificate = request.getCastProofCertificate();
			MultipartFile pwdProofCertificate = request.getPwdProofCertificate();
			
			//String idProofImageNameCustom =request.getUshaSurveyId()+"_"+"idProofImage";
			//String resdProofImageNameCustom=request.getUshaSurveyId()+"_"+"resdProofImage";
			
			if (idProofImage != null) {
				String fileUploadPath = fileUploadService.fileUpload(idProofImage);
				surveyData.setApplicantIdProofImage(fileUploadPath);
			}
			if (resdProofImage != null) {
				String fileUploadPath = fileUploadService.fileUpload(resdProofImage);
				surveyData.setApplicantResdProofImage(fileUploadPath);
			}
			if (spouseidProofImage != null) {
				String fileUploadPath = fileUploadService.fileUpload(spouseidProofImage);
				surveyData.setSpouseidProofImage(fileUploadPath);
			}
			if (ewsProofCertificate != null) {
				String fileUploadPath = fileUploadService.fileUpload(ewsProofCertificate);
				surveyData.setEwsProofCertificate(fileUploadPath);
			}
			if (castProofCertificate != null) {
				String fileUploadPath = fileUploadService.fileUpload(castProofCertificate);
				surveyData.setCastProofCertificate(fileUploadPath);
			}
			if (pwdProofCertificate != null) {
				String fileUploadPath = fileUploadService.fileUpload(pwdProofCertificate);
				surveyData.setPwdProofCertificate(fileUploadPath);
			}

			UshaSurveyEntity save = ushaSurveyRepo.save(surveyData);
			if (save.getUshaSurveyId() != null) {

				List<HouseholdEntity> householdData = new ArrayList<>();
				request.getHouseholdData().forEach(f -> {
					HouseholdEntity householdEntity = new HouseholdEntity();
					householdEntity.setActiveStatus(f.getActiveStatus());
					householdEntity.setAge(f.getAge());
					householdEntity.setActiveStatus(f.getActiveStatus());
					householdEntity.setContactDetails(f.getContactDetails());
					householdEntity.setGender(f.getGender());
					householdEntity.setName(f.getName());
					householdEntity.setOccupation(f.getOccupation());
					householdEntity.setRelationToApplicant(f.getRelationToApplicant());
					householdEntity.setUshaSurveyId(ushaSurveyRepo.findById(save.getUshaSurveyId()).get());
					householdEntity.setDependantToApplicant(f.getDependantToApplicant());
					householdData.add(householdEntity);
				});

				/*
				 * for (addHouseHoldData addHouseHoldData : householdData) {
				 * householdEntity.setAge(addHouseHoldData.getAge());
				 * householdEntity.setContactDetails(addHouseHoldData.getContactDetails());
				 * householdEntity.setDependantToApplicant(addHouseHoldData.
				 * getDependantToApplicant());
				 * householdEntity.setGender(addHouseHoldData.getGender());
				 * householdEntity.setName(addHouseHoldData.getName());
				 * householdEntity.setOccupation(addHouseHoldData.getOccupation());
				 * householdEntity.setRelationToApplicant(addHouseHoldData.
				 * getRelationToApplicant());
				 * householdEntity.setUshaSurveyId(addHouseHoldData.getUshaSurveyId()); }
				 */
				houseHoldRepo.saveAll(householdData);
			}
			if (save != null) {
				msg = "success";
			} else {
				msg = "failed";
			}
		}
		return msg;

	}

	@Override
	public List<UshaSurveyResponse> getAllUshaSurveyData() {
		List<UshaSurveyEntity> surveyDataList = ushaSurveyRepo.findAll().stream()
				.filter(f -> f.getActiveStatus() == true).collect(Collectors.toList());
		// long count = surveyDataList.stream().count();
		List<UshaSurveyResponse> list = new ArrayList<>();
		for (UshaSurveyEntity ushaSurveyEntity : surveyDataList) {
			UshaSurveyResponse response = new UshaSurveyResponse();
			response.setHouseHoldNo(ushaSurveyEntity.getHouseNo());
			response.setApplicantName(ushaSurveyEntity.getApplicantName());
			// response.setSurveyCount(count);
			list.add(response);
		}

		return list;
	}

	@Override
	public Optional<UshaSurveyEntity> getUshaSurveyDataById(Integer id) {
		Optional<UshaSurveyEntity> surveyDataList = ushaSurveyRepo.findById(id);
		return surveyDataList;
	}

	@Override
	public List<IdentityProofResponse> getIdentityProofByIdType(Integer idProofType) {
		List<IdentitytProofEntity> identity = identityProofRepo.getIdentityProofByIdType(idProofType);
		List<IdentityProofResponse> identityProofByIdType = new ArrayList<IdentityProofResponse>();
		identity.stream().forEach(x -> {
			IdentityProofResponse response = new IdentityProofResponse();
			response.setIdentityId(x.getIdentityProofId());
			response.setIdentityProofName(x.getIdProofName());
			identityProofByIdType.add(response);
		});
		return identityProofByIdType;
	}

	@Override
	public List<UshaSurveyResponse> getApplicantInfoByMunicipalityAndStatusId(Integer municipalityId,
			Integer statusId) {
		List<UshaSurveyResponse> list = new ArrayList<>();
		try {

			List<UshaSurveyEntity> applicantInfoByMunicipalityAndStatusId = ushaSurveyRepo
					.getApplicantInfoByMunicipalityAndStatusId(municipalityId, statusId);
			// List<Integer> slumIds =
			// applicantInfoByMunicipalityAndStatusId.stream().map(f->f.getSlumType().getSlumId()).collect(Collectors.toList());
			applicantInfoByMunicipalityAndStatusId.stream().forEach(x -> {
				UshaSurveyResponse response = new UshaSurveyResponse();
				response.setSurveyorName(ushaSurveyRepo.getByServeyorId(x.getServeyorId().getId()));
				response.setUshaSurveyId(x.getUshaSurveyId());
				response.setApplicantName(x.getApplicantName());
				response.setParentSpouseName(x.getParentSpouseName());
				response.setIdProofNo(x.getApplicantIdProofNo());
				response.setIdProofType(x.getApplicantIdProofType().getIdProofType());
				response.setIdProofName(x.getApplicantIdProofType().getIdProofName());
				response.setHouseHoldNo(x.getHouseNo());
				response.setSlumName(slumRepo.findById(x.getSlumType().getSlumId()).get().getSlumName());
				response.setCreatedOn(x.getCreatedOn().toString());
				list.add(response);
			});

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return list;
	}

	@Override
	public List<UshaSurveyResponse> getApplicantInfoBySurveyorId(Integer surveyorId) {
		List<UshaSurveyEntity> applicantInfoBySurveyorId = ushaSurveyRepo.getApplicantInfoBySurveyorId(surveyorId);
		List<UshaSurveyResponse> list = new ArrayList<>();
		applicantInfoBySurveyorId.stream().forEach(x -> {
			UshaSurveyResponse response = new UshaSurveyResponse();
			response.setUshaSurveyId(x.getUshaSurveyId());
			response.setApplicantName(x.getApplicantName());
			response.setSlumId(x.getSlumType().getSlumId());
			response.setSlumName(x.getSlumType().getSlumName());
			response.setMunicipalityName(x.getMunicipalityId().getMunicipalityName());
			response.setHouseHoldNo(x.getHouseNo());
			response.setStatusId(x.getStatus().getStatusId());
			list.add(response);
		});
		return list;
	}

	@Override
	public List<UshaSurveyResponse> getAllInformationofAllSurveyData() {
		List<UshaSurveyEntity> dataList = ushaSurveyRepo.findAll();
		List<UshaSurveyResponse> list = new ArrayList<>();
		dataList.stream().forEach(x -> {
			UshaSurveyResponse response = new UshaSurveyResponse();
			response.setUshaSurveyId(x.getUshaSurveyId());
			response.setApplicantName(x.getApplicantName());
			response.setSlumId(x.getSlumType().getSlumId());
			response.setSlumName(x.getSlumType().getSlumName());
			response.setMunicipalityName(x.getMunicipalityId().getMunicipalityName());
			response.setHouseHoldNo(x.getHouseNo());
			response.setStatusId(x.getStatus().getStatusId());
			list.add(response);
		});
		return list;
	}

	@Override
	public List<HouseholdResponse> getAllInformationByushaSurveyId(Integer ushaSurveyId) {
		List<HouseholdResponse> responseList = new ArrayList<HouseholdResponse>();
		Optional<UshaSurveyEntity> opt = ushaSurveyRepo.findById(ushaSurveyId);
		Optional<LRCapplicationEntity> list1 = lrcRepo.getByUshaSurveyId(ushaSurveyId);
		// Optional<HouseholdEntity>list2 =
		// houseHoldRepo.getByUshaSurveyId(ushaSurveyId);
		// List<LRCapplicationEntity> listLrcDetail ;
		List<HouseholdEntity> listHouseDetail;

		if (opt.isPresent()) {
			UshaSurveyEntity data = opt.get();
			LRCapplicationEntity list = new LRCapplicationEntity();
			BeanUtils.copyProperties(data, list);
		}
		return null;
	}

	@Override
	public String updateStatusByStatusIdUshaId(Integer statusId, Integer ushaSurveyId) {
		Optional<UshaSurveyEntity> opt = ushaSurveyRepo.findById(ushaSurveyId);
		try {
			if (opt.isPresent()) {
				UshaSurveyEntity data = opt.get();
				data.setStatus(statusRepo.findById(statusId).get());
				data.setUpdated_on(LocalDate.now());
				UshaSurveyEntity save = ushaSurveyRepo.save(data);
				if (save != null)
					return "success";
				else
					return "failed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "error";

	}

	@Override
	public List<UshaSurveyResponse> getushaSurveyInfoBySurveyorAndStatusId(Integer surveyorId, Integer statusId) {
		List<UshaSurveyResponse> list = new ArrayList<>();
		try {

			List<UshaSurveyEntity> applicantInfoBySurveyorAndStatusId = ushaSurveyRepo
					.getushaSurveyInfoBySurveyorAndStatusId(surveyorId, statusId);
			applicantInfoBySurveyorAndStatusId.stream().forEach(x -> {
				UshaSurveyResponse response = new UshaSurveyResponse();
				response.setUshaSurveyId(x.getUshaSurveyId());
				response.setApplicantName(x.getApplicantName());
				response.setParentSpouseName(x.getParentSpouseName());
				response.setIdProofNo(x.getApplicantIdProofNo());
				response.setIdProofType(x.getApplicantIdProofType().getIdProofType());
				response.setIdProofName(x.getApplicantIdProofType().getIdProofName());
				response.setHouseHoldNo(x.getHouseNo());
				response.setSlumName(slumRepo.findById(x.getSlumType().getSlumId()).get().getSlumName());
				response.setNote(x.getNote());
				list.add(response);
			});

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return list;
	}

	@Override
	public String updateStatusByStatusIdUshaId(UshaSurveyRequest request) {
		Optional<UshaSurveyEntity> opt = ushaSurveyRepo.findById(request.getUshaSurveyId());
		Optional<HouseholdEntity> opt1 = houseHoldRepo.getByUshaSurveyId(request.getUshaSurveyId());
		if (opt.isPresent()) {
			UshaSurveyEntity data = opt.get();
			BeanUtils.copyProperties(request, data);
			HouseholdEntity data1 = opt1.get();
			BeanUtils.copyProperties(request.getHouseholdData(), data1);
			UshaSurveyEntity save = ushaSurveyRepo.save(data);
			return save != null ? "success" : "failed";
		}
		return "InvalidId";
	}

	@Override
	public String updateNoteByUshaSurveyId(Integer ushaSurveyId, String note) {
		Optional<UshaSurveyEntity> opt = ushaSurveyRepo.findById(ushaSurveyId);
		if (opt.isPresent()) {
			UshaSurveyEntity data = opt.get();
			data.setNote(note);
			UshaSurveyEntity save = ushaSurveyRepo.save(data);
			return save != null ? "success" : "failed";
		}
		return "InvalidId";
	}

	String msg = null;

	@Override
	public String updateSurveyDataByUshaSurveyId(UshaSurveyRequest request) {

		Optional<UshaSurveyEntity> data = ushaSurveyRepo.findById(request.getUshaSurveyId());
		if (data != null) {
			data.stream().forEach(x -> {
				UshaSurveyEntity surveyData = data.get();
				surveyData.setStreetLane(request.getStreetLane());
				surveyData.setHouseNo(request.getHouseNo());
				surveyData.setApplicantName(request.getApplicantName());
				surveyData.setApplicantMobile(request.getApplicantMobile());
				surveyData.setApplicantGender(request.getApplicantGender());
				surveyData.setParentSpouseName(request.getParentSpouseName());
				surveyData.setParentSpouseMobile(request.getParentSpouseMobile());
				surveyData.setFamilyAnualIncome(request.getFamilyAnualIncome());
				surveyData.setAnualIncomeProof(request.getAnualIncomeProof());
				surveyData.setEwsProof(request.getEwsProof());
				surveyData.setEwsProofType(request.getEwsProofType());
				surveyData.setCastCategory(request.getCastCategory());
				surveyData.setCastCertificate(request.getCastCertificate());
				surveyData.setPwd(request.getPwd());
				surveyData.setPwdCertificate(request.getPwdCertificate());
				surveyData.setReligion(request.getReligion());
				surveyData.setApplicantIdProofType(identityProofRepo.findById(request.getApplicantIdProofType()).get());
				surveyData.setApplicantIdProofNo(request.getApplicantIdProofNo());
				surveyData.setApplicantIdProofIssueDate(LocalDate.parse(request.getApplicantIdProofIssueDate()));
				surveyData.setApplicantResdProofType(
						identityProofRepo.findById(request.getApplicantResdProofType()).get());
				surveyData.setApplicantResdProofNo(request.getApplicantResdProofNo());
				surveyData.setApplicantResdProofIssueDate(LocalDate.parse(request.getApplicantResdProofIssueDate()));
				surveyData.setSpouseIdProofType(identityProofRepo.findById(request.getSpouseIdProofType()).get());
				surveyData.setSpouseIdProofNo(request.getSpouseIdProofNo());
				surveyData.setSpouseIdProofIssueDate(LocalDate.parse(request.getSpouseIdProofIssueDate()));
				surveyData.setWallType(request.getWallType());
				surveyData.setRoofType(request.getRoofType());
				surveyData.setHouseHaveToilet(request.getHouseHaveToilet());
				surveyData.setDwellingAccessToPuccaPaver(request.getDwellingAccessToPuccaPaver());
				surveyData.setLandOwnershipInAnyUlb(request.getLandOwnershipInAnyUlb());
				surveyData.setLandOwnershipInSameUlb(request.getLandOwnershipInSameUlb());
				// surveyData.setServeyorId(userRepo.findById(request.getServeyorId()).get());
				// surveyData.setAssigneeId(userRepo.findById(request.getAssigneeId()).get());
				// surveyData.setAssignDate(LocalDate.now());
				// surveyData.setReportedDate(LocalDate.now());
				//surveyData.setStatus(statusRepo.findById(request.getStatus()).get());
				surveyData.setActiveStatus(request.getActiveStatus());
				surveyData.setUpdated_on(LocalDate.now());				
				MultipartFile idProofImage = request.getApplicantIdProofImage();
				MultipartFile resdProofImage = request.getApplicantResdProofImage();
				MultipartFile spouseidProofImage = request.getSpouseidProofImage();
				MultipartFile ewsProofCertificate = request.getEwsProofCertificate();
				MultipartFile castProofCertificate = request.getCastProofCertificate();
				MultipartFile pwdProofCertificate = request.getPwdProofCertificate();

				if (idProofImage != null) {
					String fileUploadPath = fileUploadService.fileUpload(idProofImage);
					surveyData.setApplicantIdProofImage(fileUploadPath);
				}
				if (resdProofImage != null) {
					String fileUploadPath = fileUploadService.fileUpload(resdProofImage);
					surveyData.setApplicantResdProofImage(fileUploadPath);
				}
				if (spouseidProofImage != null) {
					String fileUploadPath = fileUploadService.fileUpload(spouseidProofImage);
					surveyData.setSpouseidProofImage(fileUploadPath);
				}
				if (ewsProofCertificate != null) {
					String fileUploadPath = fileUploadService.fileUpload(ewsProofCertificate);
					surveyData.setEwsProofCertificate(fileUploadPath);
				}
				if (castProofCertificate != null) {
					String fileUploadPath = fileUploadService.fileUpload(castProofCertificate);
					surveyData.setCastProofCertificate(fileUploadPath);
				}
				if (pwdProofCertificate != null) {
					String fileUploadPath = fileUploadService.fileUpload(pwdProofCertificate);
					surveyData.setPwdProofCertificate(fileUploadPath);
				}

//				if (!idProofImage.isEmpty()) {
//					Path fileStorageLocation = Paths.get(filelocation).toAbsolutePath().normalize();
//					try {
//						Files.createDirectories(fileStorageLocation);
//					} catch (IOException e) {
//						e.printStackTrace();
//						throw new RuntimeException("issue with creating file directory");
//					}
//					String idProofImagEfileName = StringUtils.cleanPath(idProofImage.getOriginalFilename());
//					// Path filePath = Paths.get(fileStorageLocation + "\\" + idProofImagEfileName);
//					Path filePath = Paths.get(fileStorageLocation + "/" + idProofImagEfileName);
//					try {
//						surveyData.setApplicantIdProofImage(idProofImagEfileName);
//						// Check if the file's name contains invalid characters
//						if (idProofImagEfileName.isEmpty() || idProofImagEfileName.contains("..")) {
//							throw new FileStorageException(
//									"Sorry! Filename contains invalid path sequence or file is empty"
//											+ idProofImagEfileName);
//						} else {
//							// Copy file to the target location (Replacing existing file with the same name)
//							Files.copy(idProofImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//							Set<PosixFilePermission> perms = Files.readAttributes(filePath, PosixFileAttributes.class)
//									.permissions();
//							perms.add(PosixFilePermission.OWNER_READ);
//							perms.add(PosixFilePermission.GROUP_READ);
//							perms.add(PosixFilePermission.OTHERS_READ);
//
//							Files.setPosixFilePermissions(filePath, perms);
//						}
//
//					} catch (IOException ex) {
//						throw new FileStorageException("Could not store file " + idProofImage + ". Please try again!",
//								ex);
//					}
//				}
//
//				if (!resdProofImage.isEmpty()) {
//					Path fileStorageLocation = Paths.get(filelocation).toAbsolutePath().normalize();
//					try {
//						Files.createDirectories(fileStorageLocation);
//					} catch (IOException e) {
//						e.printStackTrace();
//						throw new RuntimeException("issue with creating file directory");
//					}
//					String resdProofImagEfileName = StringUtils.cleanPath(resdProofImage.getOriginalFilename());
//					// Path filePath = Paths.get(fileStorageLocation + "\\" +
//					// resdProofImagEfileName);
//					Path filePath = Paths.get(fileStorageLocation + "/" + resdProofImagEfileName);
//					try {
//						surveyData.setApplicantIdProofImage(resdProofImagEfileName);
//						// Check if the file's name contains invalid characters
//						if (resdProofImagEfileName.isEmpty() || resdProofImagEfileName.contains("..")) {
//							throw new FileStorageException(
//									"Sorry! Filename contains invalid path sequence or file is empty"
//											+ resdProofImagEfileName);
//						} else {
//							// Copy file to the target location (Replacing existing file with the same name)
//							Files.copy(resdProofImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//							Set<PosixFilePermission> perms = Files.readAttributes(filePath, PosixFileAttributes.class)
//									.permissions();
//							perms.add(PosixFilePermission.OWNER_READ);
//							perms.add(PosixFilePermission.GROUP_READ);
//							perms.add(PosixFilePermission.OTHERS_READ);
//							Files.setPosixFilePermissions(filePath, perms);
//						}
//
//					} catch (IOException ex) {
//						throw new FileStorageException("Could not store file " + resdProofImage + ". Please try again!",
//								ex);
//					}
//				}
//
//				if (!spouseidProofImage.isEmpty()) {
//					Path fileStorageLocation = Paths.get(filelocation).toAbsolutePath().normalize();
//					try {
//						Files.createDirectories(fileStorageLocation);
//					} catch (IOException e) {
//						e.printStackTrace();
//						throw new RuntimeException("issue with creating file directory");
//					}
//					String spouseidProofImagefileName = StringUtils.cleanPath(spouseidProofImage.getOriginalFilename());
//					// Path filePath = Paths.get(fileStorageLocation + "\\" +
//					// spouseidProofImagefileName);
//					Path filePath = Paths.get(fileStorageLocation + "/" + spouseidProofImagefileName);
//					try {
//
//						surveyData.setSpouseidProofImage(spouseidProofImagefileName);
//						// Check if the file's name contains invalid characters
//						surveyData.setApplicantResdProofImage(spouseidProofImagefileName);
//						if (spouseidProofImagefileName.isEmpty() || spouseidProofImagefileName.contains("..")) {
//							throw new FileStorageException(
//									"Sorry! Filename contains invalid path sequence or file is empty"
//											+ spouseidProofImagefileName);
//						} else {
//							// Copy file to the target location (Replacing existing file with the same name)
//							Files.copy(spouseidProofImage.getInputStream(), filePath,
//									StandardCopyOption.REPLACE_EXISTING);
//							Set<PosixFilePermission> perms = Files.readAttributes(filePath, PosixFileAttributes.class)
//									.permissions();
//							perms.add(PosixFilePermission.OWNER_READ);
//							perms.add(PosixFilePermission.GROUP_READ);
//							perms.add(PosixFilePermission.OTHERS_READ);
//							Files.setPosixFilePermissions(filePath, perms);
//						}
//
//					} catch (IOException ex) {
//						throw new FileStorageException(
//								"Could not store file " + spouseidProofImage + ". Please try again!", ex);
//					}
//				}

				UshaSurveyEntity save = ushaSurveyRepo.save(surveyData);
				if (save != null) {
					msg = "success";
				} else {
					msg = "failed";
				}
				if (save.getUshaSurveyId() != null) {					
					List<HouseholdEntity> householdData = new ArrayList<>();
					Integer size = request.getHouseholdData().size();
					if (size > 0) {
						request.getHouseholdData().forEach(f -> {
							Optional<HouseholdEntity> houseHoldId = houseHoldRepo.findById(f.getHouseHoldId());
							if (houseHoldId.isPresent()) {
								HouseholdEntity householdEntity = houseHoldId.get();
								// householdEntity.setHouseholdId(f.getHouseHoldId());
								householdEntity.setActiveStatus(f.getActiveStatus());
								householdEntity.setAge(f.getAge());
								householdEntity.setActiveStatus(f.getActiveStatus());
								householdEntity.setContactDetails(f.getContactDetails());
								householdEntity.setGender(f.getGender());
								householdEntity.setName(f.getName());
								householdEntity.setOccupation(f.getOccupation());
								householdEntity.setRelationToApplicant(f.getRelationToApplicant());
								householdEntity.setUshaSurveyId(ushaSurveyRepo.findById(save.getUshaSurveyId()).get());
								householdEntity.setDependantToApplicant(f.getDependantToApplicant());
								houseHoldRepo.save(householdEntity);
								householdData.add(householdEntity);
							}

						});// forEach HouseHold
					}

					houseHoldRepo.saveAll(householdData);
				}

			});// forEach UshaSurvey

		}
		return msg;
	}
}