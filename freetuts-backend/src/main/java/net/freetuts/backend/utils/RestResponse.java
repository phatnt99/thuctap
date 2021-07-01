package net.freetuts.backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The Class RestResponse.
 *
 * @author PhatNT15
 */
public class RestResponse {

	/**
	 * Send ok.
	 *
	 * @param obj the obj
	 * @return the response entity
	 */
	public static ResponseEntity<?> sendOk(Object obj) {

		return ResponseEntity.ok(obj);
	}

	/**
	 * Send created.
	 *
	 * @param obj the obj
	 * @return the response entity
	 */
	public static ResponseEntity<?> sendCreated(Object obj) {

		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}

	/**
	 * Send no content.
	 *
	 * @return the response entity
	 */
	public static ResponseEntity<?> sendNoContent() {

		return ResponseEntity.noContent().build();
	}

}
