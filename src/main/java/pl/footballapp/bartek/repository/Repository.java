package pl.footballapp.bartek.repository;

public interface Repository {

    DatabaseConnection connection = DatabaseConnection.getInstance();

    String getTableName();

    // todo na podobnej zasadzie jak metoda select napisać metodę insert i delete

    default String select(boolean where, boolean end, String... columns) {

        StringBuilder sb = new StringBuilder();

        if (columns == null || columns.length == 0) {
            sb.append("SELECT * FROM ").append(getTableName());
        } else {
            sb.append("SELECT ");
            boolean hasColumn = false;

            for (int i = 0; i < columns.length; i++) {
                String col = columns[i];
                if (col != null && !col.trim().isEmpty()) {
                    if (hasColumn) {
                        sb.append(", ");
                    }
                    sb.append(col.trim());
                    hasColumn = true;
                }
            }

            if (!hasColumn) {
                sb.append("SELECT * FROM ").append(getTableName());
            } else {
                sb.append(" FROM ").append(getTableName());
            }
        }

        if (where) {
            sb.append(" WHERE ");
        } else if (end) {
            sb.append(";");
        }

        return sb.toString();
    }
}
