package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDrivingDetailsDto {

    private int driverId;
    private boolean driverStatus;
    private int driverCarId;
    private BigDecimal latitude;
    private BigDecimal longitude;

}

