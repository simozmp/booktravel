package logic.model.bookingstate;

import logic.model.Booking;

/**
 * 
 * @author metal
 *
 * This class represent the inactive state, the last state of the 
 * booking life cycle.
 */
public class InactiveState implements BookingState {

	@Override
	public void accept(Booking context) {
		
	}

	@Override
	public void delete(Booking context) {
		
	}

	@Override
	public void resubmit(Booking context) {
		
	}

	@Override
	public StateEnum getEnumValueOfState() {
		return StateEnum.INACTIVE;
	}



}
