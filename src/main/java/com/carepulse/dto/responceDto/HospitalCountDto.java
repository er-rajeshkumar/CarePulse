package com.carepulse.dto.responceDto;

public class HospitalCountDto {
    private Long hospitalId;
    private String hospitalName;
    private Long count;

    public HospitalCountDto() {}

    public HospitalCountDto(Long hospitalId,
                            String hospitalName,
                            Long count) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.count = count;
    }

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
    
    
}
