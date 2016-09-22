import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


/*
 * ExaServer
 * 
 * This class provides the backbone of the game,
 * processing requests from different players
 * 
 */

public class ExaServer {

  static int updateCount = 0;
  static ArrayList<Player> players;
  static List map;
  final int SLEEP_TIME = 70;
  final int MAX_PLAYERS = 30;
  final int PORT_NUMBER = 8520;
  static int counter = 0;

	public static void main(String[] args) {
		new ExaServer();
	}

	public ExaServer() {
		players = new ArrayList<Player>();
		map = Collections.synchronizedList(new ArrayList<Entity>());
		new Listener().start();
		new Updater().start();
		runGame();
	}

	public void runGame() {
		while (true) {
			try {
				Thread.sleep(Constants.Socket.SERVER_REFRESH);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			for (int i = 0; i < players.size(); i++) {
				try {
					players.get(i).getOutput()
					.writeObject(Constants.entityToMessage(map));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class Listener extends Thread {
		ServerSocket socket;

		public Listener() {
			try {
				socket = new ServerSocket(PORT_NUMBER);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void run() {
			while (true) {
				try {
					new Player(socket.accept()).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class Updater extends Thread {
		public void run() {
			ArrayList<Point2D.Double> locations = new ArrayList<Point2D.Double>();
			while (true) {
				updateCount++;
				try {
					Thread.sleep(Constants.Socket.UPDATE_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// System.out.println(updateCount);
				// System.out.println("Updating");
				for (int i = 0; i < map.size(); i++) {
					try {
						((Entity) (map.get(i))).updateLocation();
						locations.add(((Entity) (map.get(i))).getLocation());
						if (hasDuplicate(locations)){
							for (int x = 0; x < locations.size(); x++){
								
							}
						}
						// System.out.println(((Entity)
						// (map.get(i))).getResultant());
						// System.out.println(((Entity)
						// (map.get(i))).getLocation());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public boolean hasDuplicate(ArrayList<Point2D.Double> listOfPoints){
	        Set inputSet = new HashSet(listOfPoints);
	        if (inputSet.size() < listOfPoints.size()){
	        	return true;
	        }
	        return false;
	    }

	}

	private class Player extends Thread {
		private Entity entity;
		private Socket socket;
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private int ID;

		public Player(Socket socket) {
			this.ID = getID();
			this.socket = socket;
			try {
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
				output.writeInt(ID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			players.add(this);
		}

		public ObjectOutputStream getOutput() {
			return output;
		}

		public int getID() {
			return counter++;
		}

		public void run() {
			Entity temp;
			while (true) {
				try {

					temp = new Entity((Message) input.readObject());
					if (map.indexOf(entity) != -1) {
						entity.set(temp);
					} else{
						entity = temp.copy();
						map.add(entity);
						}
						
					//	map.remove(map.indexOf(entity));
					
					//map.add(temp);
					//entity = temp;

					//System.out.println(map.size());

				} catch (ClassNotFoundException e) {
					//System.out.println("YOU HAVE NO CLASS");
					e.printStackTrace();
				} catch (IOException e) {
					//System.out.println("Io exception");
				}
			}
		}
	}

}