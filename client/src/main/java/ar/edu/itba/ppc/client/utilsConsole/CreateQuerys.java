package ar.edu.itba.ppc.client.utilsConsole;

import ar.edu.itba.tp1g5.CareCompleted;
import ar.edu.itba.tp1g5.RoomStatus;
import ar.edu.itba.tp1g5.WaitingRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CreateQuerys {
    private static final Logger logger = LoggerFactory.getLogger(CreateQuerys.class);

    public static void queryRoomStatusFile(List<RoomStatus> roomStatuses, String outPath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Room,Status,Patient,Doctor\n");
        for (RoomStatus row : roomStatuses) {
            stringBuilder.append(row.getRoomNumber()).append(",")
                    .append(row.getRoomStatus()).append(",")
                    .append(row.getPatientName()).append(" (").append(row.getPatientLevel()).append("),")
                    .append(row.getDoctorName()).append(" (").append(row.getDoctorLevel()).append(")\n");
        }
        if (ClientUtils.createOutputFile(outPath, stringBuilder.toString())) {
            logger.info("Room status CSV file created successfully");
            ClientUtils.printCSVData(outPath);
        }
    }

    public static void queryWaitingRoomFile(List<WaitingRoom> waitingRooms, String outPath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Patient,Level\n");
        for (WaitingRoom row : waitingRooms) {
            stringBuilder.append(row.getPatientName()).append(",")
                    .append(row.getPatientLevel()).append("\n");
        }
        if (ClientUtils.createOutputFile(outPath, stringBuilder.toString())) {
            logger.info("Waiting room CSV file created successfully");
            ClientUtils.printCSVData(outPath);
        }
    }

    public static void queryCaresFile(List<CareCompleted> completedCares, String outPath) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Room,Patient,Doctor\n");
        for (CareCompleted row : completedCares) {
            stringBuilder.append(row.getRoomNumber()).append(",")
                    .append(row.getPatientName()).append(" (").append(row.getPatientLevel()).append("),")
                    .append(row.getDoctorName()).append(" (").append(row.getDoctorLevel()).append(")\n");
        }
        if (ClientUtils.createOutputFile(outPath, stringBuilder.toString())) {
            logger.info("Completed cares CSV file created successfully");
            ClientUtils.printCSVData(outPath);
        }
    }
}