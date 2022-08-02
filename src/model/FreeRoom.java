package model;

public class FreeRoom extends Room {

        public FreeRoom() {
            super(0.0);
        }

        public boolean isFree() {
            return true;
        }

        public String toString() {
            return "Room " + this.getRoomNumber() + ": $" + this.getRoomPrice() + ", " + this.getRoomType() + ", " + (this.isFree() ? "free" : "Not Free.");
        }
}
