package univ.lille.gl.sra1.web.dto;

public class SimpleResponse {

	public String message;
	public Status status = Status.OK;

	public enum Status {
		OK, ERROR
	};


}
