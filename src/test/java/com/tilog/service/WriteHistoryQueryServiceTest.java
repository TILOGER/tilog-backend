package com.tilog.service;

import com.tilog.dto.history.WriteHistoryDailyCountResponse;
import com.tilog.dto.history.WriteHistoryDailyCountSummaryResponse;
import com.tilog.entity.WriteHistory;
import com.tilog.repository.WriteHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class WriteHistoryQueryServiceTest {
    @Mock
    private WriteHistoryRepository writeHistoryRepository;

    @InjectMocks
    private WriteHistoryQueryService writeHistoryQueryService;

    @Test
    @DisplayName("기간별 작성 개수를 조회하면 기록이 없는 날짜는 0으로 채워진다")
    void getDailyCounts_fillMissingDatesWithZero() {
        // Given
        Long memberId = 1L;
        LocalDate startDate = LocalDate.of(2026, 5, 1);
        LocalDate endDate = LocalDate.of(2026, 5, 5);

        WriteHistory day1 = WriteHistory.builder().writtenDate(startDate).writeCount(1).build();
        WriteHistory day3 = WriteHistory.builder().writtenDate(startDate.plusDays(2)).writeCount(2).build();

        BDDMockito.given(writeHistoryRepository.findAllByMemberIdAndWrittenDateBetweenOrderByWrittenDateAsc(memberId, startDate, endDate))
                .willReturn(List.of(day1, day3));

        // When
        WriteHistoryDailyCountSummaryResponse response = writeHistoryQueryService.getDailyCounts(memberId, startDate, endDate);

        // Then
        assertThat(response.items()).hasSize(5);

        assertDailyCount(response.items().get(0), "2026-05-01", 1);
        assertDailyCount(response.items().get(1), "2026-05-02", 0);
        assertDailyCount(response.items().get(2), "2026-05-03", 2);
    }

    // 검증용 헬퍼 메서드
    private void assertDailyCount(WriteHistoryDailyCountResponse item, String expectedDate, int expectedCount) {
        assertThat(item.date()).isEqualTo(LocalDate.parse(expectedDate));
        assertThat(item.writeCount()).isEqualTo(expectedCount);
    }
}
