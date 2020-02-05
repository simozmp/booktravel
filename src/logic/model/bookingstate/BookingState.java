package logic.model.bookingstate;

import logic.model.Booking;

/**
 * 
 * @author metal
 *
 *         The interface that must be implemented by states. This interface
 *         contains operation that can be performed on a booking.
 */
public interface BookingState {

	public void accept(Booking context);

	public void delete(Booking context);

	public void resubmit(Booking context);

	public StateEnum getEnumValueOfState();

}
