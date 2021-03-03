package com.nonamefm.queue.service.queue;

import com.nonamefm.queue.dto.queue.web.SongInput;

public interface SongQueue {

    void addSong(SongInput song);

}
