package cn.edu.xidian.www.mymusicpro.music;

/**
 * Created by chenyudong on 2017/5/7.
 */

public class SongInfo {

    /**
     * 歌曲名
     */
    public String song;

    /**
     * 歌手
     */
    public String singer;

    /**
     * 歌曲的地址  （推荐歌曲这儿存放网络地址）
     */
    public String path;
    /**
     * 歌曲长度
     */
    public int duration;
    /**
     * 歌曲的大小
     */
    public long size;

//    public SongInfo(SongInfo newinfo) {
//        this.song = newinfo.song;
//        this.singer = newinfo.singer;
//        this.path = newinfo.path;
//        this.duration = newinfo.duration;
//        this.size = newinfo.size;
//    }
//    public SongInfo() {
//        this.song = "";
//        this.singer = "";
//        this.path = "";
//        this.duration = 0;
//        this.size = 0;
//    }
}
