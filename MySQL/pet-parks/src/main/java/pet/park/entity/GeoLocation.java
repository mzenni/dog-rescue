package pet.park.entity;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table
@Embeddable
@Data
@NoArgsConstructor
public class GeoLocation {
	private BigDecimal latitude;
	private BigDecimal longitude; 
	
	public GeoLocation(GeoLocation geoLocation) {
		this.latitude = geoLocation.latitude;
		this.longitude = geoLocation.longitude; 
	}
}
