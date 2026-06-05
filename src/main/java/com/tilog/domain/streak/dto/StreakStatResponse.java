<<<<<<< HEAD
package com.tilog.domain.streak.dto;

import com.tilog.domain.streak.entity.StreakStat;

import java.time.LocalDate;

public record StreakStatResponse(
        Long memberId,
        int currentStreak,
        int longestStreak,
        int totalWrittenDays,
        LocalDate lastWrittenDate
) {
    public static StreakStatResponse from(StreakStat streakStat) {
        return new StreakStatResponse(
                streakStat.getMemberId(),
                streakStat.getCurrentStreak(),
                streakStat.getLongestStreak(),
                streakStat.getTotalWrittenDays(),
                streakStat.getLastWrittenDate()
        );
    }

    public static StreakStatResponse empty(Long memberId) {
        return new StreakStatResponse(
                memberId,
                0,
                0,
                0,
                null
        );
    }
}
=======
package com.tilog.domain.streak.dto;

import com.tilog.domain.streak.entity.StreakStat;

import java.time.LocalDate;

public record StreakStatResponse(
        Long memberId,
        int currentStreak,
        int longestStreak,
        int totalWrittenDays,
        int totalTilCount,
        LocalDate lastWrittenDate
) {
    public static StreakStatResponse from(StreakStat streakStat, int totalTilCount) {
        return new StreakStatResponse(
                streakStat.getMemberId(),
                streakStat.getCurrentStreak(),
                streakStat.getLongestStreak(),
                streakStat.getTotalWrittenDays(),
                totalTilCount,
                streakStat.getLastWrittenDate()
        );
    }

    public static StreakStatResponse empty(Long memberId) {
        return new StreakStatResponse(
                memberId,
                0,
                0,
                0,
                0,
                null
        );
    }
}
>>>>>>> ef056bd7f9c29c323b580cf7af6dfb33f6c9bf51
