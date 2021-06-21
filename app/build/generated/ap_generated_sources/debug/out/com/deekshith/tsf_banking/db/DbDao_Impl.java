package com.deekshith.tsf_banking.db;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DbDao_Impl implements DbDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DbEntity> __insertionAdapterOfDbEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBalance;

  public DbDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbEntity = new EntityInsertionAdapter<DbEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `customer` (`id`,`name`,`email`,`balance`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbEntity value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.email == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.email);
        }
        stmt.bindLong(4, value.balance);
      }
    };
    this.__preparedStmtOfUpdateBalance = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE customer SET balance=balance+? WHERE id=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final DbEntity dbEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbEntity.insert(dbEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateBalance(final int amt, final int Id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBalance.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, amt);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, Id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateBalance.release(_stmt);
    }
  }

  @Override
  public List<DbEntity> getAll() {
    final String _sql = "SELECT * FROM customer";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
      final List<DbEntity> _result = new ArrayList<DbEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbEntity _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final int _tmpBalance;
        _tmpBalance = _cursor.getInt(_cursorIndexOfBalance);
        _item = new DbEntity(_tmpId,_tmpName,_tmpEmail,_tmpBalance);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public DbEntity getById(final int Id) {
    final String _sql = "SELECT * FROM customer where id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, Id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfBalance = CursorUtil.getColumnIndexOrThrow(_cursor, "balance");
      final DbEntity _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final int _tmpBalance;
        _tmpBalance = _cursor.getInt(_cursorIndexOfBalance);
        _result = new DbEntity(_tmpId,_tmpName,_tmpEmail,_tmpBalance);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
