package com.nonamefm.queue.service.queue;

import com.nonamefm.queue.domains.user.Account;
import com.nonamefm.queue.domains.user.AccountSettings;
import com.nonamefm.queue.dto.queue.web.SongInput;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SongQueueImpl implements SongQueue {


    private static final Map<String, Map<String, Integer>> streamerUser = new HashMap<>();

    @Override
    public void addSong(SongInput song) {

    }

    private boolean checkUserCount(Account account, SongInput song) {
        AccountSettings settings = account.getAccountSettings();
        return streamerUser.get(account.getName()).get(song.getNickName()) > settings.getCountSongs();
    }
}
