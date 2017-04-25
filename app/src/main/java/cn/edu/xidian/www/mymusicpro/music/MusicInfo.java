package cn.edu.xidian.www.mymusicpro.music;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by 陈昱东 on 2017/4/23.
 */

public class MusicInfo {
    public String music_name;
    public String music_author;
    protected Context mContext;
    //TODO what should the MusicInfo include.

    /**
     * 扫描本地音乐  并且添加到本地数据库
     */
    // TODO: 2017/4/23 扫描mp3文件  
    /*
    private boolean scanMusicToSQLite() {
        ContentResolver cr = mContext.getContentResolver();
        StringBuffer select = new StringBuffer(" 1=1 ");
        // 查询语句：检索出.mp3为后缀名，时长大于1分钟，文件大小大于1MB的媒体文件
        if (Environment.isFilterSize(mContext)) {
            select.append(" and " + MediaStore.Audio.Media.SIZE + " > " + FILTER_SIZE);
        }
        if (Environment.isFilterDuration(mContext)) {
            select.append(" and " + MediaStore.Audio.Media.DURATION + " > " + FILTER_DURATION);
        }
        final Cursor cursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj_music,
                select.toString(), null,
                MediaStore.Audio.Media.TITLE_KEY);

        while (cursor.moveToNext()) {

            final DaoSession session = SweetApplication.getDaoSession();

            final MusicInfoDao musicInfoDao = session.getMusicInfoDao();
            final AlbumInfoDao albumInfoDao = session.getAlbumInfoDao();
            final ArtistInfoDao artistInfoDao = session.getArtistInfoDao();

            final MusicInfo musicInfo = new MusicInfo();
            musicInfo.setSongId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            musicInfo.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            musicInfo.setAlbumId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
            musicInfo.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
            musicInfo.setArtistId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID)));
            musicInfo.setDuration(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
            musicInfo.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
            musicInfo.setFavorite(false);

            final AlbumInfo albumInfo = getAlbumById(mContext, musicInfo.getAlbumId());

            final ArtistInfo artistInfo = getArtistInfoById(mContext, musicInfo.getArtistId());

            try {
                session.runInTx(new Runnable() {
                    @Override
                    public void run() {
                        if (musicInfo == null) {
                            return;
                        }

                        if (musicInfoDao.load(musicInfo.getSongId()) == null) {
                            musicInfoDao.insert(musicInfo);
                        }

                        if (albumInfoDao.load(musicInfo.getAlbumId()) == null && albumInfo != null) {
                            albumInfoDao.insertOrReplace(albumInfo);
                        }

                        if (artistInfoDao.load(musicInfo.getArtistId()) == null && artistInfo != null) {
                            artistInfoDao.insertOrReplace(artistInfo);
                        }
                    }
                });

                Message msg = Message.obtain();
                msg.what = MSG_SCAN_ING;
                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY_BUNDLE_MUSICINFO, musicInfo);
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            } catch (Exception e) {
                Log.i(TAG, "Exception:" + e.toString());
                e.printStackTrace();
                mHandler.sendEmptyMessage(MSG_SCAM_FAIL);
                return false;
            }

        }

        mHandler.sendEmptyMessage(MSG_SCAN_SUCCESS);
        return true;
    }
    */
}
