package logic.model.bookingstate;

import java.time.LocalDate;

import logic.bean.BookingBean;
import logic.model.Booking;
import logic.model.dao.BookingDAOImpl;

/**
 * 
 * @author metal
 *
 * This class represent the deleted state of a booking.
 */
public class DeletedState implements BookingState {

	/**
	 * A deleted booking cannot be accepted.
	 */
	@Override
	public void accept(Booking context) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * A deleted booking can't be deleted yet.
	 */
	@Override
	public void delete(Booking context) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * A booking can be resubmitted if the check-in date is before today.
	 */
	@Override
	public void resubmit(Booking context) {
		
		if(context.getCheckIn().isAfter(LocalDate.now())) {
			context.setState(new SubmittedState());
			BookingBean bean = new BookingBean();
			bean.setBookingId(context.getId());
			bean.setState(StateEnum.SUBMITTED);
			
			new BookingDAOImpl().updateBooking(bean);
		}
		
	}

	@Override
	public StateEnum getEnumValueOfState() {
		return StateEnum.DELETED;
	}

}
