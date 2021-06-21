package com.deekshith.tsf_banking.db;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TransacDao_Impl implements TransacDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransacEntity> __insertionAdapterOfTransacEntity;

  public TransacDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransacEntity = new EntityInsertionAdapter<TransacEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `transaction` (`key`,`sendId`,`recId`,`amt`,`sendName`,`recName`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransacEntity value) {
        if (value.key == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.key);
        }
        stmt.bindLong(2, value.sendId);
        stmt.bindLong(3, value.recId);
        stmt.bindLong(4, value.amt);
        if (value.sendName == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.sendName);
        }
        if (value.recName == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.recName);
        }
      }
    };
  }

  @Override
  public void insert(final TransacEntity transacEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransacEntity.insert(transacEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<TransacEntity> getAll() {
    final String _sql = "SELECT * FROM `transaction`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
      final int _cursorIndexOfSendId = CursorUtil.getColumnIndexOrThrow(_cursor, "sendId");
      final int _cursorIndexOfRecId = CursorUtil.getColumnIndexOrThrow(_cursor, "recId");
      final int _cursorIndexOfAmt = CursorUtil.getColumnIndexOrThrow(_cursor, "amt");
      final int _cursorIndexOfSendName = CursorUtil.getColumnIndexOrThrow(_cursor, "sendName");
      final int _cursorIndexOfRecName = CursorUtil.getColumnIndexOrThrow(_cursor, "recName");
      final List<TransacEntity> _result = new ArrayList<TransacEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TransacEntity _item;
        final Long _tmpKey;
        if (_cursor.isNull(_cursorIndexOfKey)) {
          _tmpKey = null;
        } else {
          _tmpKey = _cursor.getLong(_cursorIndexOfKey);
        }
        final int _tmpSendId;
        _tmpSendId = _cursor.getInt(_cursorIndexOfSendId);
        final int _tmpRecId;
        _tmpRecId = _cursor.getInt(_cursorIndexOfRecId);
        final int _tmpAmt;
        _tmpAmt = _cursor.getInt(_cursorIndexOfAmt);
        final String _tmpSendName;
        _tmpSendName = _cursor.getString(_cursorIndexOfSendName);
        final String _tmpRecName;
        _tmpRecName = _cursor.getString(_cursorIndexOfRecName);
        _item = new TransacEntity(_tmpKey,_tmpSendId,_tmpRecId,_tmpAmt,_tmpSendName,_tmpRecName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
