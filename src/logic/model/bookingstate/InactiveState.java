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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Booking context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resubmit(Booking context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StateEnum getEnumValueOfState() {
		return StateEnum.INACTIVE;
	}



}
