import { Button, FormControlLabel, Radio } from "@mui/material";
import Paper, { PaperProps } from "@mui/material/Paper";
import { FC } from "react";
import { Input, RadioGroup } from "../components";
import { BlackList } from "./BlackList";
import {
  apiKeysStringSelector,
  ipStringSelector,
  setApiKeysString,
  setIpString,
} from "../../../app/settingsData/settingsData";
import { useAppDispatch, useAppSelector } from "../../../app/hooks";

export const Settings: FC<PaperProps> = ({ sx }) => {
  const dispatch = useAppDispatch();
  const apiKeysString = useAppSelector(apiKeysStringSelector);
  const ipString = useAppSelector(ipStringSelector);

  return (
    <Paper
      sx={{
        p: 1,
        columnGap: "2rem",
        gridTemplateColumns: "1.2fr 1fr",
        rowGap: "2rem",
        gridTemplateRows: "45vh 32vh 4.7vh",
        gridTemplateAreas: ` 
        "links       blackList"
        "keys        blackList"   
        "saveButton  blackList"`,
        ...sx,
      }}
    >
      <Input
        sx={{ gridArea: "links" }}
        label={"Ссылки-источники"}
        multiline
        value={ipString}
        onChange={(e) => {
          dispatch(setIpString((e.target as HTMLInputElement).value));
        }}
        rows={15.2}
      />
      <Input
        sx={{ gridArea: "keys" }}
        label={"Ключи API от ipqualityscore.com"}
        multiline
        value={apiKeysString}
        onChange={(e) => {
          dispatch(setApiKeysString((e.target as HTMLInputElement).value));
        }}
        rows={9.8}
      />
      {/* ///////////////////////////////////////// */}
      {/* <Input
        sx={{ gridArea: "proxy" }}
        label={"Прокси для запросов"}
        value={"socks5://3211:6543211_country-us@geo.iproyal.com:12321"}
      />
      <Input
        sx={{ gridArea: "flowsNumber" }}
        label={"Количество потоков"}
        value={"3"}
      />
      <RadioGroup
        sx={{ gridArea: "proxyType" }}
        label={"Тип проксей"}
        value={"3"}
      >
        <FormControlLabel value="socks5" control={<Radio />} label="socks5" />
        <FormControlLabel value="http" control={<Radio />} label="http" />
      </RadioGroup> */}

      <Button
        variant="contained"
        sx={{ gridArea: "saveButton" }}
        onClick={() => {}}
      >
        Сохранить
      </Button>
      <BlackList sx={{ gridArea: "blackList" }} />
    </Paper>
  );
};
