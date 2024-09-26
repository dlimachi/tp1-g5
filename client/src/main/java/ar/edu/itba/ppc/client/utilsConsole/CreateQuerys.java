package ar.edu.itba.ppc.client.utilsConsole;

import ar.edu.itba.tp1g5.CareCompleted;
import ar.edu.itba.tp1g5.RoomStatus;
import ar.edu.itba.tp1g5.WaitingRoom;

import java.util.List;

public class CreateQuerys {
    public static void queryRoomStatusFile(List<RoomStatus> roomStatuses, String outPath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Room,Status,Patient,Doctor\n");
        for (RoomStatus row : roomStatuses) {
            stringBuilder.append(row.getRoomNumber()).append(",").append(row.getRoomStatus()).append(",");
            if(row.getRoomStatus().equals("free")){
                stringBuilder.append(",,\n");
            }
            else {
                stringBuilder.append(row.getPatientName()).append(" (" + row.getPatientLevel() + ")").append(",")
                        .append(row.getDoctorName()).append(" (" + row.getDoctorLevel() + ")").append("\n");
            }
        }
        ClientUtils.createOutputFile(outPath, stringBuilder.toString());
    }

    public static void queryWaitingRoomFile(List<WaitingRoom> roomStatuses, String outPath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Patient,Level\n");
        for (WaitingRoom row : roomStatuses) {
            stringBuilder.append(row.getPatientName()).append(",")
                    .append(row.getPatientLevel()).append("\n");
        }
        ClientUtils.createOutputFile(outPath, stringBuilder.toString());
    }

    public static void queryCaresFile(List<CareCompleted> roomStatuses, String outPath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Room,Patient,Doctor\n");
        for (CareCompleted row : roomStatuses) {
            stringBuilder.append(row.getRoomNumber()).append(",")
                        .append(row.getPatientName()).append(" (" + row.getPatientLevel() + ")").append(",")
                        .append(row.getDoctorName()).append(" (" + row.getDoctorLevel() + ")").append("\n");

        }
        ClientUtils.createOutputFile(outPath, stringBuilder.toString());
    }
}
